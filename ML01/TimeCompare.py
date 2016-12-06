#coding:utf-8   
  
  
import numpy as np   
import matplotlib.pyplot as plt   
import time 
import nltk 
from sklearn.cluster import KMeans,AffinityPropagation   
from sklearn.datasets.samples_generator import make_blobs   
  
# 生成测试数据   
np.random.seed(0)   
centers = [[1, 1], [-1, -1], [1, -1]]   
gaac_time = []   
ap_time = []   
for n in [50,100,500,1000]:   
    X, labels_true = make_blobs(n_samples=n, centers=centers, cluster_std=0.7)   
  
    # 计算GAAC算法时间      
    t0 = time.time()
    ga=nltk.cluster.gaac.GAAClusterer(num_clusters=5,normalise=True)
    ga.cluster(vectors=X)
    gaac_time.append([n,(time.time() - t0)]) 
    # 计算AP算法时间   
    ap = AffinityPropagation()   
    t0 = time.time()   
    ap.fit(X)   
    ap_time.append([n,(time.time() - t0)])   
  
print ('K-Means time',gaac_time[:10])   
print ('AP time',ap_time[:10])   
# 图形展示   
ga_mat = np.array(gaac_time)   
ap_mat = np.array(ap_time)   
plt.figure()   
plt.bar(np.arange(4), ga_mat[:,1], width = 0.3, color = 'b', label = 'GAAClusterer', log = 'True')   
plt.bar(np.arange(4)+0.3, ap_mat[:,1], width = 0.3, color = 'g', label = 'AffinityPropagation', log = 'True')   
plt.xlabel('Sample Number')   
plt.ylabel('Computing time')   
plt.title('GAAClusterer and AffinityPropagation computing time ')   
plt.legend(loc='upper center')   
plt.show()   
  