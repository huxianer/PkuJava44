
import xml.dom.minidom
from numpy import *

def textParse(bigString):
	import re
	listOfTokens = re.split(r'\W*', bigString)
	return [tok.lower() for tok in listOfTokens if len(tok) > 2]
	
def createVocabList(dataSet):
	vocabSet = set([])
	for document in dataSet:
		vocabSet = vocabSet | set(document)
	return list(vocabSet)
	
def setOfWords2Vec(vocabList, inputSet):
	returnVec = [0]*len(vocabList)
	for word in inputSet:
		if word in vocabList:
			returnVec[vocabList.index(word)] = 1
		else:
			print "the word: %s is not in my Vocabulary!" % word
	return returnVec
	
def textExtract():		
	termList=[[]]; categoryList=[[]]; cate1=[]; cate2=[]; cate3=[]; cate4=[]; cate0=[]
	
	from xml.etree import ElementTree as ET
	per = ET.parse('C:\Users\LiuYiXia\Desktop\ABSA2014\Train\Restaurants_Train.xml')
	p = per.findall('./sentence')
	for oneper in p:
		termL=[]
		termLText=[]
		categoryL=[]
		categoryLi=[]
		polarityL=[]
		polarityLi=[]
		flag0 = 0; flag1=0; flag2=0; flag3=0; flag4=0
		
		for child in oneper.getchildren():			
			if child.tag == 'text':
				wordList = child.text
				termL = textParse(wordList)
			if child.tag == 'aspectCategories':
				o = child.findall('./aspectCategory')		
				for threeper in o:
					categoryValue = threeper.get("category")
					i = categoryValue
					if i == 'food' and flag0 == 0:
						cate0.append(1)
						flag0 = 1
					elif i == 'service' and flag1 == 0:
						cate1.append(1)
						flag1 = 1
					elif i == 'price' and flag2 == 0:
						cate2.append(1)
						flag2 = 1
					elif i == 'ambience' and flag3 == 0:
						cate3.append(1)
						flag3 = 1
					elif i == 'anecdotes/miscellaneous' and flag4 == 0:
						flag4 = 1
						cate4.append(1)					
					categoryL.append(categoryValue)
				if(flag0 == 0):
					cate0.append(0)
				if(flag1 == 0):
					cate1.append(0)
				if(flag2 == 0):
					cate2.append(0)
				if(flag4 == 0):
					cate4.append(0)
				if(flag3 == 0):
					cate3.append(0)
		
		termList.append(termL)		
	categoryList.append(cate0)
	categoryList.append(cate1)
	categoryList.append(cate2)
	categoryList.append(cate3)
	categoryList.append(cate4)
	return termList, categoryList
	
def sigmoid(inX):
	return 1.0/(1+exp(-inX))
	
def stocGradAscent1(dataMatrix, classLabels, numIter=150):
	m,n = shape(dataMatrix)
	weights = ones(n)
	for j in range(numIter):	
		dataIndex = range(m)
		for i in range(m):
			alpha = 4/(1.0+j+i)+0.01
			randIndex = int(random.uniform(0, len(dataIndex)))
			h = sigmoid(sum(dataMatrix[randIndex]*weights))
			error = classLabels[randIndex] - h
			weights = weights + alpha * error * dataMatrix[randIndex]
			del(dataIndex[randIndex])
	return weights
	
def classifyVector(inX, weights):
	prob = sigmoid(sum(inX*weights))
	if prob > 0.5:
		res = 1.0
	else:
		res = 0.0
	return res, prob
	
def Max(p1,p2,p3,p4,p5):
	mm = p1
	ff = 0
	if(mm<p2):
		mm=p2
		ff=1
	if(mm<p3):
		mm=p3
		ff=2
	if(mm<p4):
		mm=p4
		ff=3
	if(mm<p5):
		mm=p5
		ff=4
	return ff
	
def testingNB():
	trainWeightList=[[]]
	classSum = 5
	listOPosts,listClasses = textExtract()
	myVocabList = createVocabList(listOPosts)
	trainMat=[]
	cateFL=['food', 'service', 'price', 'ambience', 'anecdotes/miscellaneous']
	flag = 0
	for postinDoc in listOPosts:
		if flag == 1:
			trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
		flag = 1
	
	for i in range(classSum):
		trainW = stocGradAscent1(array(trainMat), listClasses[i+1], 200)
		trainWeightList.append(trainW)
	
	from xml.dom.minidom import Document
	
	doc = Document()
	sentences = doc.createElement('sentences') 
	doc.appendChild(sentences)

	from xml.etree import ElementTree as ET
	per = ET.parse('C:\Users\LiuYiXia\Desktop\ABSA2014\Test_PhaseA\Restaurants_Test_PhaseA.xml')
	p = per.findall('./sentence')
	for oneper in p:
		sentence = doc.createElement('sentence')
		sentences.appendChild(sentence)	
		cateList=[]
		probList=[]
		for child in oneper.getchildren():
			if child.tag == 'text':
				wordList = child.text
				testEntry = textParse(wordList)
				thisDoc = setOfWords2Vec(myVocabList, testEntry)
				
				for iii in range(classSum):
					temp0,prob0 = classifyVector(array(thisDoc), trainWeightList[iii+1])
					cateList.append(temp0)
					probList.append(prob0)
				
				text = doc.createElement('text')
				text_text = doc.createTextNode(wordList)
				text.appendChild(text_text)
				sentence.appendChild(text)
				
				aspectCategories = doc.createElement('aspectCategories')
				sentence.appendChild(aspectCategories)
		
				if cateList[0]==0 and cateList[1]==0 and cateList[2]==0 and cateList[3]==0 and cateList[4]==0: 
					fflag = Max(probList[0],probList[1],probList[2],probList[3],probList[4])
					aspectCategory = doc.createElement('aspectCategory')
					aspectCategory.setAttribute('category',cateFL[fflag])
					aspectCategories.appendChild(aspectCategory)
				else:
					for ii in range(len(cateList)):
						if cateList[ii] == 1.0:										
							aspectCategory = doc.createElement('aspectCategory')
							aspectCategory.setAttribute('category',cateFL[ii])
							aspectCategories.appendChild(aspectCategory)
				sentence.appendChild(aspectCategories)
				
	f = open(r'E:\FilePy\categoryLogistic.xml','w')
	f.write(doc.toprettyxml(indent = ''))
	f.close()
