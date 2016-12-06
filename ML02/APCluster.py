#coding:utf-8   
  
  
import numpy as np   
import matplotlib.pyplot as plt   
import time  
from sklearn import metrics  
from sklearn.cluster import KMeans,AffinityPropagation   
from sklearn.datasets.samples_generator import make_blobs   

def test():
	name ="D:/test_data/James_Hamilton"
	line = []
	kmeans_time = []   
	ap_time = []
	for i in range(0, 100):
		num_ = num(i)
		f = open(name + '/' + num_ + '.txt', 'r')
		content = f.read().strip()
		items = content.split(" ")
		line_one = []
		for item in items:
			line_one.append(float(item))

		line.append(line_one)

    #print line[0]
	labelL=[]
	af = AffinityPropagation(preference=-1.05).fit(line)   
	cluster_centers_indices = af.cluster_centers_indices_   
	labels = af.labels_ 
	new_X = np.column_stack((line, labels))
	
	#print labels[3]
	myl=labels.tolist()
	print myl
	n_clusters_ = len(cluster_centers_indices)     
	print('Estimated number of clusters: %d' % n_clusters_)   
	#print("Homogeneity: %0.3f" % metrics.homogeneity_score(labels_true, labels))   
	#print("Completeness: %0.3f" % metrics.completeness_score(labels_true, labels))   
	#print("V-measure: %0.3f" % metrics.v_measure_score(labels_true, labels))   
	#print("Adjusted Rand Index: %0.3f"   
		  #% metrics.adjusted_rand_score(labels_true, labels))   
	#print("Adjusted Mutual Information: %0.3f"   
		  #% metrics.adjusted_mutual_info_score(labels_true, labels))   
	#print("Silhouette Coefficient: %0.3f"   
		  #% metrics.silhouette_score(line, labels, metric='sqeuclidean'))   
	#print('Top 10 sapmles:',new_X[:10])   
	  
	
	
	
	import xml.dom.minidom

	#在内存中创建一个空的文档
	doc = xml.dom.minidom.Document() 
	#创建一个根节点Managers对象
	root = doc.createElement('clustering') 
	#设置根节点的属性
	root.setAttribute('name', 'James_Hamilton') 
	
	#将根节点添加到文档对象中
	doc.appendChild(root) 

	dataSet1={}
	mylist=[]
	mylist2=[]
	data=[]
	#for i in managerList :
	#for m in range(124):
	
		#data.append(m)
    #print(key,value)
	dataSet1={m:myl[m] for m in range(100)}
	print dataSet1.keys()
	print dataSet1.values()
	print dataSet1.items()
	mylist=delfunc(myl)
	print mylist
	print len(mylist)
	for j in mylist:
		nodeManager = doc.createElement('entity')
		nodeManager.setAttribute('id', str(j)) 
		
		for x in range(100):
			if(dataSet1.get(x)==j):
				nodeName = doc.createElement('doc')
				nodeName.setAttribute('rank',str(x))
				nodeManager.appendChild(nodeName)
				root.appendChild(nodeManager)
	#开始写xml文档
	fp = open(r'E:\AP2\James_Hamilton.clust.xml','w')
	doc.writexml(fp, indent='\t', addindent='\t', newl='\n', encoding="utf-8")
	
def delfunc(resList1):
	resList = [];
	#resList1 = [1,2,3,4,5,1,3,5]
	resList2 = []
	for i in range(len(resList1)):
		if(resList1.count(resList1[i]) >=1 ):
			if(resList2.count(resList1[i]) >= 1):
				continue
			else:
				resList2.append(resList1[i])
	#print resList2
	return resList2 

def num(num):
    if num >= 0 and num < 10:
        return "00" + str(num)
    if num >= 10 and num < 100:
        return "0" + str(num)
    else:
        return str(num)