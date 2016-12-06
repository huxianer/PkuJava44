#coding=utf-8
import nltk
import sys
import codecs
import os
import csv
from parser import PreHandle
from urlparse import *

from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer

reload(sys)
sys.setdefaultencoding('utf-8')

def ner():
    pre = PreHandle()
    name = "James_Hamilton"

    ran = 100
    path = "test/web_pages/" + name + "/" + name + ".xml"

    for i in range(0,ran):
        print i
        num_ = num(i)
        print str(num_)

        f = codecs.open(name + '_tmp/' + num_ + '.txt', 'r', 'utf-8')
        content = f.read()
        # content = content.encode('utf-8').strip()

        # 将文本拆分成句子列表
        sens = nltk.sent_tokenize(content)
        print sens

        words = []
        for sent in sens:
            # 对句子进行分词
            words.append(nltk.word_tokenize(sent))

        print words

        # 词性标注
        tags = []
        for tokens in words:
            tags.append(nltk.pos_tag(tokens))

        print tags

        # NER需要利用词性标注的结果
        ners = nltk.ne_chunk(tags[0])

        if os.path.isdir(name):
            print "the dir exists"
        else:
            os.mkdir(name)

        if os.path.isdir(name+'_'):
            print "the dir exists"
        else:
            os.mkdir(name+'_')

        path_trans = name + '/' + num_ + '.txt'
        f_ = open(name + '_/' + num_ + '_.txt', 'w')

        url, title, snippet = PreHandle.parseForTrainXml(pre, path, i)
        r = urlparse(url)

        ws = []
        for word in str(r.path).replace('/', ' ').replace(".html","").lstrip().split(' '):
                ws.append(word)
        print str(r.path).replace('/', ' ').replace(".html"," ").lstrip().split(' ')
        f_.write(str(r.path).replace('/', ' ').replace(".html", "").lstrip())
        f_.write(" ")

        # csvfile = file(name + '/' + num_ +'.csv', 'wb')
        # writer = csv.writer(csvfile)

        print str(ners)
        print "###"


        for s in ners:
            if type(s) is nltk.tree.Tree:
                for l in s.leaves():
                    print l[0]
                    f_.write(l[0] + " ")
                    # writer.writerow(l)
                    ws.append(l[0])



        # print ws

        # if len(ws) < 2:
        #     print "none"
        #     result = codecs.open(path_trans, 'w', 'utf-8')
        #     result.close()
        # else :
        #     # 将文本中的词语转换为词频矩阵 矩阵元素a[i][j] 表示j词在i类文本下的词频
        #     vectorizer = CountVectorizer()
        #
        #     # 该类会统计每个词语的tf-idf权值
        #     transformer = TfidfTransformer()
        #
        #     # 第一个fit_transform是计算tf-idf 第二个fit_transform是将文本转为词频矩阵
        #     tfidf = transformer.fit_transform(vectorizer.fit_transform(ws))
        #
        #     # 获取词袋模型中的所有词语
        #     word = vectorizer.get_feature_names()
        #
        #     # 将tf-idf矩阵抽取出来，元素w[i][j]表示j词在i类文本中的tf-idf权重
        #     weight = tfidf.toarray()
        #
        #     result = codecs.open(path_trans, 'w', 'utf-8')
        #     for j in range(len(word)):
        #         result.write(word[j] + ' ')
        #     result.write('\r\n\r\n')
        #
        #     # 打印每类文本的tf-idf词语权重，第一个for遍历所有文本，第二个for便利某一类文本下的词语权重
        #     for i in range(len(weight)):
        #         # print u"-------这里输出第", i, u"类文本的词语tf-idf权重------"
        #         for j in range(len(word)):
        #             result.write(str(weight[i][j]) + ' ')
        #         result.write('\r\n\r\n')
        #
        #     result.close()
    tfIdf(name, ran)

def num(num):
    if num >= 0 and num < 10:
        return "00" + str(num)
    if num >= 10 and num < 100:
        return "0" + str(num)
    else:
        return str(num)

def getFilelist(argv) :
    path = argv[1]
    filelist = []
    files = os.listdir(path)
    for f in files :
        if(f[0] == '.') :
            pass
        else :
            filelist.append(f)
    return filelist,path

def tfIdf(name, ran) :

    corpus = []  # 存取文档的分词结果
    for i in range(0,ran):
        num_ = num(i)
        fname = name + '_/' + num_ + '_.txt'
        f = open(fname, 'r+')
        content = f.read()
        f.close()
        corpus.append(content)

    vectorizer = CountVectorizer()
    transformer = TfidfTransformer()
    tfidf = transformer.fit_transform(vectorizer.fit_transform(corpus))
    word = vectorizer.get_feature_names()
    weight = tfidf.toarray()

    for i in range(len(weight)):
        num__ = num(i)
        f_ = open(name + '/' + num__ + '.txt', 'w+')
        for j in range(len(word)):
            # f_.write(word[j] + " ")
            # f_.write(str(weight[i][j]) + "\r\n")
            f_.write(str(weight[i][j]) + " ")

        f_.close()

ner()