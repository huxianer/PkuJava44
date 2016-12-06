#coding:utf-8   
  
  
import numpy as np   
import matplotlib.pyplot as plt   
import time 
import nltk 
from numpy import array
from sklearn import metrics  
from sklearn.cluster import KMeans,AffinityPropagation   
from sklearn.datasets.samples_generator import make_blobs   

def test():
	name = "D:/test_data/James_Hamilton"
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
	#af = AffinityPropagation(preference=-1).fit(line)   
	#cluster_centers_indices = af.cluster_centers_indices_   
	#labels = af.labels_ 
	#new_X = np.column_stack((line, labels))
	labels=[]
	datas=[array(v) for v in line]
	ga=nltk.cluster.gaac.GAAClusterer(num_clusters=5,normalise=True)
	ga.cluster(vectors=datas)
	#ga.dendrogram().show()
	#print labels[3]
	for data in datas:
		label=ga.classify(data)
		labels.append(label)
	myl=labels
	#print labels
	print len(myl)
	
	
	import xml.dom.minidom

	#���ڴ��д���һ���յ��ĵ�
	doc = xml.dom.minidom.Document() 
	#����һ�����ڵ�Managers����
	root = doc.createElement('clustering') 
	#���ø��ڵ������
	root.setAttribute('name', 'James_Hamilton') 
	
	#�����ڵ���ӵ��ĵ�������
	doc.appendChild(root) 

	dataSet1={}
	mylist=[]
	mylist2=[]
	data=[]
	#for i in managerList :
	#for m in range(124):
	
		#data.append(m)
    #print(key,value)
	dataSet1={m:labels[m] for m in range(100)}
	print dataSet1.keys()
	print dataSet1.values()
	print dataSet1.items()
	mylist=delfunc(labels)
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
	#��ʼдxml�ĵ�
	fp = open(r'E:\GAAC1\James_Hamilton.clust.xml','w')
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