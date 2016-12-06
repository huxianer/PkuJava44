import numpy as np
import nltk
import string



def kmeans():


	line = []
	dataSet={}
	for i in range(0, 124):
		#num_ = num(i)
		f = open(r"D:/Abby_Watkins/%03d.txt" %i, 'r')
		content = f.read().strip()
		items = content.split(" ")
		line_one = []
		for item in items:
			line_one.append(float(item))

		line.append(line_one)


	#print line
		dataSet[str(i)] = line
	#print dataSet.values()
		
	#print line
	#print dataSet.keys()

	distances={}
	for k1 in dataSet.keys():
		#distances[(key1,dataSet[key1])]=[]
		(max_score,most_similar)=(0.0,(None,None))
		for k2 in range(len(dataSet[k1])):
			for k3 in range(len(dataSet[k1])):
			#print dataSet[k1][k2]
			#dataSet[k2].shape=(2219,1)
				#if dataSet[k1][k2]!=dataSet[k1][k3]:
					distances=nltk.cluster.util.cosine_distance(dataSet[k1][k2],dataSet[k1][k3])
					if dataSet[k1][k2]==dataSet[k1][k3]:
						continue
					if distances>max_score:
						(max_score,most_similar)=(distances,(k1,dataSet[k1][k3]))
						print '''Most similar to %s (%s)\t%s(%s)
						\tscore %score
						'''%(k1,dataSet[k1][k2],most_similar[0],most_similar[1],max_score)

def num(num):
    if num >= 0 and num < 10:
        return "00" + str(num)
    if num >= 10 and num < 100:
        return "0" + str(num)
    else:
        return str(num)


