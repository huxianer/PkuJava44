# -*- coding: utf-8 -*-
#来自网络
import os
import sys
import timeit

import numpy
import theano
import theano.tensor as T
from theano.tensor.signal import downsample
from theano.tensor.nnet import conv
#from logistic_sgd import LogisticRegression, load_data
from mlp import HiddenLayer,LR as LogisticRegression,load_data

#先从main函数： evaluate_lenet5()看起
#该函数实现了卷积+采样（池化）的过程
class LeNetConvPoolLayer(object):
    """卷积层+采样层"""
#输入的参数
    def __init__(self, rng, input, filter_shape, image_shape, poolsize=(2, 2)):
        """
        rng,input。
	filter_shape: 长度为4的元组或list
        filter_shape: (过滤器数目, 输入特征图数目, 过滤器高度, 过滤器宽度)

        image_shape: 长度为4的元组或list
        image_shape: (样本块大小, 输入特征图数目, 图像高度, 图像宽度)

        poolsize: 长度为2的元组或list
        poolsize: 下采样的shape大小(#rows, #cols)
        """

# 断言，确定image_shape的1号元素与filter_shape的1号元素相等 如不成立会引发一个错误
# 因为从以上定义中可以知道，这个元素代表输入特征图的数量
        assert image_shape[1] == filter_shape[1] 
        self.input = input

        # prod()返回元素之积。如果filter_shape=(2,4,3,3),
        # 那么filter_shape[1:]=(4,3,3)
        # prod(filter_shape[1:])=4*3*3=36
	#（这里试根据tutorial来的）
        fan_in = numpy.prod(filter_shape[1:])
        # each unit in the lower layer receives a gradient from:
        # "num output feature maps * filter height * filter width" /
        #   pooling size
        fan_out = (filter_shape[0] * numpy.prod(filter_shape[2:]) /
                   numpy.prod(poolsize))
                   
        # 用随机均匀分布初始化权值W
        W_bound = numpy.sqrt(6. / (fan_in + fan_out))
#theano.shared 就是一个共享变量，在程序的任何地方都可以用，而且值相同theano.shared（值，类型）
#numpy.asarray 将列表转化为数组
#numpy.random.RandomState.uniform 统一随机生成数据的范围（）
	'''
	Draw samples from the distribution:
	>>>

	>>> s = np.random.uniform(-1,0,1000)

	All values are within the given interval:
	>>>

	>>> np.all(s >= -1)
	True
	>>> np.all(s < 0)
	True


	'''
        self.W = theano.shared(
            numpy.asarray(
                rng.uniform(low=-W_bound, high=W_bound, size=filter_shape),
                dtype=theano.config.floatX
            ),
            borrow=True
        )

        # 每一张输出特征图都有一个一维的偏置值，初始化为0。偏置也是属于共享变量的
        b_values = numpy.zeros((filter_shape[0],), dtype=theano.config.floatX)
        self.b = theano.shared(value=b_values, borrow=True)

        # 将输入特征图与过滤器进行卷积操作 
        conv_out = conv.conv2d(
            input=input,
            filters=self.W,
            filter_shape=filter_shape,
            image_shape=image_shape
        )

        # 用maxpooling方法下采样每一个张特征图 池化
        pooled_out = downsample.max_pool_2d(
            input=conv_out,
            ds=poolsize,
            ignore_border=True
        )

        # 先把偏置进行张量扩张，由1维扩展为4维张量(1*2*1*1)    
        # 再把扩展后的偏置累加到采样输出
        # 把累加结果送入tanh非线性函数得到本层的网络输出
        self.output = T.tanh(pooled_out + self.b.dimshuffle('x', 0, 'x', 'x'))

        # store parameters of this layer
        self.params = [self.W, self.b]

        # keep track of model input
        self.input = input

#原始参数：learning_rate：学习速率 n_epochs：就是整个数据集 mnist.pkl.gz中全部重复学习次数
#卷积核的个数：nkerns=[20, 50]【nkerns[0]是layer1中卷积核个数，同理nkernes[1]为layer2】
#每一次学习输入的数据：batch_size=500张 后面会计算出 n_train_batches 为100次：500*100=50000张训练图
def evaluate_lenet5(learning_rate=0.1, n_epochs=1000,
                    dataset='mydata.pkl.gz',
                    nkerns=[20, 50], batch_size=10):
    """ Demonstrates lenet on MNIST dataset
    实验数据集是MNIST数据集。  
    :type learning_rate: float
    :param learning_rate: learning rate used (factor for the stochastic
                          gradient)

    :type n_epochs: int
    :param n_epochs: maximal number of epochs to run the optimizer
    n_epochs是最大迭代次数。一次完整迭代包括计算完所有完整数据，即(总数size/batch_size)次
    
    :type dataset: string
    :param dataset: path to the dataset used for training /testing (MNIST here)
    数据集路径
    
    :type nkerns: list of ints
    :param nkerns: number of kernels on each layer
    卷积核数目。第一个下采样层有20个卷积核，第二个下采样有50个卷积核。
    一个卷积核经过卷积计算会生成一张特征图。
    (我认为卷积核就相当于神经元的个数，对应着权值的元素个数)
    """
    rng = numpy.random.RandomState(23455)#产生随机数

    datasets = load_data(dataset)#最终返回的是一个数组，里面有三类数据，三类标签 【训练、验证、测试】

    train_set_x, train_set_y = datasets[0]#训练集
    valid_set_x, valid_set_y = datasets[1]#验证集
    test_set_x, test_set_y = datasets[2]#测试集

    # compute number of minibatches （计算需要多少次进行） for training, validation and testing 
#得到各训练集中的总数
    n_train_batches = train_set_x.get_value(borrow=True).shape[0]
    n_valid_batches = valid_set_x.get_value(borrow=True).shape[0]
    n_test_batches = test_set_x.get_value(borrow=True).shape[0]
#计算以batch_size为一批计算需要多少次迭代
    n_train_batches /= batch_size
    n_valid_batches /= batch_size
    n_test_batches /= batch_size

    # allocate symbolic variables for the data
#这里的index也是个变量，后面的minibatch_index会对其赋值，表示选择第几批数据，每批batch_size个图像
    index = T.lscalar()  
#这里的x,y就是符号象征，相当于定义变量，后面的层中会有对应的gives[x：  y： ]与之对应赋值
    # start-snippet-1
    x = T.matrix('x')   # the data is presented as rasterized images
    y = T.ivector('y')  # the labels are presented as 1D vector of
                        # [int] labels

    ######################
    # BUILD ACTUAL MODEL #
    ######################
    print '... building the model'

# 构造第0层的输入数据。就是把形状shape为(batch_size,28*28)数据块转化为四维(batch_size,1,28,28)
# (batch_size,1，28*28)就是有batch_size行，一行对应一个样本，每行有28*28列，是对应样本的具体数据【就是构造一个大矩阵，每行放1个图，大小为28*28，放batch_size行】
#就是把刚才的x弄成 batch_size[行]*（1个×(28*28)）【列】大小（不理解见手绘图）
    layer0_input = x.reshape((batch_size, 1, 28, 28))

    # Construct the first convolutional pooling layer:（具体见cnn卷积、池化后图像尺寸的变化）
    # filtering reduces the image size to (28-5+1 , 28-5+1) = (24, 24)
    # maxpooling reduces this further to (24/2, 24/2) = (12, 12)
    # 4D output tensor is thus of shape (batch_size, nkerns[0], 12, 12)
    layer0 = LeNetConvPoolLayer(
        rng,
        input=layer0_input,
        image_shape=(batch_size, 1, 28, 28),
#一次输入batch_size张图，每张分别与一个卷积模板进行卷积（这里面的1指的是一行有1个（28*28）的图像）
        filter_shape=(nkerns[0], 1, 5, 5),#给出这个模板的大小 nkernes[0] 50个 5*5 的模板
#每张和50张模板卷积，大小为图像大小为28*28 也即输入的数据大小为batch_size[行]*1×(28*28)【列】大小
        poolsize=(2, 2)#给出池化的大小
    )

    # Construct the second convolutional pooling layer
    # filtering reduces the image size to (12-5+1, 12-5+1) = (8, 8)
    # maxpooling reduces this further to (8/2, 8/2) = (4, 4)
    # 4D output tensor is thus of shape (batch_size, nkerns[1], 4, 4)
    layer1 = LeNetConvPoolLayer(
        rng,
        input=layer0.output,
        image_shape=(batch_size, nkerns[0], 12, 12),
#第二层的输入batch_size×nkerns[0]张图片,每张大小为12*12 就是输入数据的大小为 batch_size【行】×（nkerns[0]*(12*12)）【列】
#or (500, 50 * 12 * 12) = (500, 7200)       
	filter_shape=(nkerns[1], nkerns[0], 5, 5),
#卷积核为20(nkerns[1])×50（nkerns[0]）个每个大小为5*5类比上句
         poolsize=(2, 2)
    )

    # the HiddenLayer being fully-connected, it operates on 2D matrices of
    # shape (batch_size, num_pixels) (i.e matrix of rasterized images).
    # This will generate a matrix of shape (batch_size, nkerns[1] * 4 * 4),
    # or (500, 50 * 4 * 4) = (500, 800) with the default values.
    layer2_input = layer1.output.flatten(2)

    # construct a fully-connected sigmoidal layer in mlp.py  隐层，与输入层是全连接
    layer2 = HiddenLayer(
        rng,
        input=layer2_input,
        n_in=nkerns[1] * 4 * 4,#输入数据的大小 20×4*4=320
        n_out=500,#最终输出500个向量
        activation=T.tanh#非线性激活函数
    )

    # 就是mlp.py 中的LR函数 逻辑回归 在该层中权重W以及偏置b均初始化为0（注意只是在该层中的初始化），并设置了回归函数
    layer3 = LogisticRegression(input=layer2.output, n_in=500, n_out=2)

    # the cost we minimize during training is the NLL of the model
    cost = layer3.negative_log_likelihood(y)#执行代价函数
#测试集函数
    test_model = theano.function(
        [index],#批次索引
        layer3.errors(y),#调用时执行这个计算错误的函数【测试数据集上的错误】
        givens={#输入对应的数据以及标签
            x: test_set_x[index * batch_size: (index + 1) * batch_size],
            y: test_set_y[index * batch_size: (index + 1) * batch_size]
        }
    )
#验证集函数
    validate_model = theano.function(
        [index],
        layer3.errors(y),#同理计算验证集数据错误
        givens={
            x: valid_set_x[index * batch_size: (index + 1) * batch_size],
            y: valid_set_y[index * batch_size: (index + 1) * batch_size]
        }
    )

    # create a list of all model parameters to be fit by gradient descent（梯度下降）
    params = layer3.params + layer2.params + layer1.params + layer0.params

    # create a list of gradients （梯度）for all model parameters
    grads = T.grad(cost, params)#计算代价函数cost对训练参数的导数

    # train_model is a function that updates the model parameters by
    # SGD Since this model has many parameters, it would be tedious to
    # manually create an update rule for each model parameter. We thus
    # create the updates list by automatically looping over all
    # (params[i], grads[i]) pairs.这里的updates就是用来修改内部参数的
    updates = [
        (param_i, param_i - learning_rate * grad_i)
        for param_i, grad_i in zip(params, grads)
    ]
#训练函数
    train_model = theano.function(
        [index],
        cost,
        updates=updates,
        givens={
            x: train_set_x[index * batch_size: (index + 1) * batch_size],
            y: train_set_y[index * batch_size: (index + 1) * batch_size]
        }
    )
    # end-snippet-1

    ###############
    # TRAIN MODEL #
    ###############
    print '... training'
    # early-stopping parameters
    patience = 10000  # look as this many examples regardless
    patience_increase = 2  # wait this much longer when a new best is
                           # found
#当新的验证误差是原来的0.995倍时，才会更新best_validation_loss。即误差小了，但是至少要小了0.99
    improvement_threshold = 0.995  # a relative improvement of this much is
                                   # considered significant
    validation_frequency = min(n_train_batches, patience / 2)
                                  # go through this many
                                  # minibatche before checking the network
                                  # on the validation set; in this case we
                                  # check every epoch

    best_validation_loss = numpy.inf
    best_iter = 0
    test_score = 0.
    start_time = timeit.default_timer()

    epoch = 0
    done_looping = False
#n_epochs:即(总数size/batch_size)次
    while (epoch < n_epochs) and (not done_looping):
        epoch = epoch + 1
        for minibatch_index in xrange(n_train_batches):

            iter = (epoch - 1) * n_train_batches + minibatch_index#现在一共训练的数量

            if iter % 100 == 0: #每隔100次打印一次
                print 'training @ iter = ', iter
            cost_ij = train_model(minibatch_index)#真正的调用这个函数去训练（从高层调用激活所有的层）
	    #print 'minibatch_index=',minibatch_index
#validation_frequency = min(n_train_batches, patience / 2) 每隔validation_frequency进行一次验证
            if (iter + 1) % validation_frequency == 0:

                # compute zero-one loss on validation set 这里调用验证模型去计算layer3.errors
                validation_losses = [validate_model(i) for i
                                     in xrange(n_valid_batches)]
                this_validation_loss = numpy.mean(validation_losses)#计算均值
                print('epoch %i, minibatch %i/%i, validation error %f %%' %
                      (epoch, minibatch_index + 1, n_train_batches,
                       this_validation_loss * 100.))

                # if we got the best validation score until now
                if this_validation_loss < best_validation_loss:

                    #improve patience if loss improvement is good enough
                    if this_validation_loss < best_validation_loss *  \
                       improvement_threshold:
# validation_frequency = min(n_train_batches, patience / 2) 会影响到验证集验证频率
                        patience = max(patience, iter * patience_increase)

                    # save best validation score and iteration number 更新
                    best_validation_loss = this_validation_loss
                    best_iter = iter

                    # test it on the test set 在测试集上测试
                    test_losses = [
                        test_model(i)
                        for i in xrange(n_test_batches)
                    ]
                    test_score = numpy.mean(test_losses)
                    print(('     epoch %i, minibatch %i/%i, test error of '
                           'best model %f %%') %
                          (epoch, minibatch_index + 1, n_train_batches,
                           test_score * 100.))

            if patience <= iter:
                done_looping = True
                break

    end_time = timeit.default_timer()
    print('Optimization complete.')
    print('Best validation score of %f % evaluate_lenet5()% obtained at iteration %i, '
          'with test performance %f %%' %
          (best_validation_loss * 100., best_iter + 1, test_score * 100.))
    print >> sys.stderr, ('The code for file ' +
                          os.path.split(__file__)[1] +
                          ' ran for %.2fm' % ((end_time - start_time) / 60.))

if __name__ == '__main__':
    evaluate_lenet5()


def experiment(state, channel):
    evaluate_lenet5(state.learning_rate, dataset=state.dataset)
