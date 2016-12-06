
import xml.dom.minidom
from numpy import *

def loadDataSet():
	postingList = [ ['my', 'dog', 'has', 'flea', 'problems', 'help', 'please'],
					['maybe', 'not', 'take', 'him', 'to', 'dog', 'park', 'stupid'], 
					['my', 'dalmation', 'is', 'so', 'cute', 'I', 'love', 'him'],
					['stop', 'posting', 'stupid', 'worthless', 'garbage'],
					['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],
					['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']]
	classVec = [0, 1, 2, 1, 3, 4]
	return postingList, classVec

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
	
	
def trainNB0(trainMatrix, trainCategory):
	numTrainDocs = len(trainMatrix)
	numWords = len(trainMatrix[1])
	cateNumList=[0,0,0,0,0]
	
	#print len(trainMatrix)
	#print len(trainCategory)
	#print sum(trainMatrix[3])
	
	myset = set(trainCategory)
	#cateNumList=[0]*max(trainCategory)
	for item in myset:
		cateNumList[item]=trainCategory.count(item)
		#print item,":",trainCategory.count(item)
	#print cateNumList
	
	foodNum = cateNumList[0]/float(numTrainDocs)
	serviceNum = cateNumList[1]/float(numTrainDocs)
	priceNum = cateNumList[2]/float(numTrainDocs)
	ambienceNum = cateNumList[3]/float(numTrainDocs)
	amecdoteNum = cateNumList[4]/float(numTrainDocs)
	
	p0Num = ones(numWords); p1Num = ones(numWords); p2Num = ones(numWords); p3Num = ones(numWords); p4Num = ones(numWords); 
	p0Denom = 2.0; p1Denom = 2.0; p2Denom = 2.0; p3Denom = 2.0; p4Denom = 2.0; 
	for i in range(numTrainDocs-1):
		if trainCategory[i] == 1:
			p1Num += trainMatrix[i+1]
			p1Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 2:
			p2Num += trainMatrix[i+1]
			p2Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 3:
			p3Num += trainMatrix[i+1]
			p3Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 4:
			p4Num += trainMatrix[i+1]
			p4Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 0:
			p0Num += trainMatrix[i+1]
			p0Denom += sum(trainMatrix[i+1])
	p1Vect = log(p1Num/p1Denom)
	p2Vect = log(p2Num/p2Denom)
	p3Vect = log(p3Num/p3Denom)
	p4Vect = log(p4Num/p4Denom)
	p0Vect = log(p0Num/p0Denom)
	#print p0Vect,p1Vect,p2Vect,p3Vect,p4Vect,foodNum,serviceNum,priceNum,ambienceNum,amecdoteNum
	return p0Vect,p1Vect,p2Vect,p3Vect,p4Vect,foodNum,serviceNum,priceNum,ambienceNum,amecdoteNum
	
	
	
def trainNB1(trainMatrix, trainCategory):
	numTrainDocs = len(trainMatrix)
	numWords = len(trainMatrix[1])
	cateNumList=[0,0,0,0]
	
	#print len(trainMatrix)
	#print len(trainCategory)
	#print sum(trainMatrix[3])
	
	myset = set(trainCategory)
	#cateNumList=[0]*max(trainCategory)
	for item in myset:
		cateNumList[item]=trainCategory.count(item)
		#print item,":",trainCategory.count(item)
	#print cateNumList
	
	positiveNum = cateNumList[0]/float(numTrainDocs)
	negativeNum = cateNumList[1]/float(numTrainDocs)
	conflictNum = cateNumList[2]/float(numTrainDocs)
	neutralNum = cateNumList[3]/float(numTrainDocs)
	
	
	p0Num = ones(numWords); p1Num = ones(numWords); p2Num = ones(numWords); p3Num = ones(numWords)
	p0Denom = 2.0; p1Denom = 2.0; p2Denom = 2.0; p3Denom = 2.0
	for i in range(numTrainDocs-1):
		if trainCategory[i] == 1:
			p1Num += trainMatrix[i+1]
			p1Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 2:
			p2Num += trainMatrix[i+1]
			p2Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 3:
			p3Num += trainMatrix[i+1]
			p3Denom += sum(trainMatrix[i+1])
		elif trainCategory[i] == 0:
			p0Num += trainMatrix[i+1]
			p0Denom += sum(trainMatrix[i+1])
	p1Vect = log(p1Num/p1Denom)
	p2Vect = log(p2Num/p2Denom)
	p3Vect = log(p3Num/p3Denom)
	p0Vect = log(p0Num/p0Denom)
	#print p0Vect,p1Vect,p2Vect,p3Vect,p4Vect,foodNum,serviceNum,priceNum,ambienceNum,amecdoteNum
	return p0Vect,p1Vect,p2Vect,p3Vect,positiveNum,negativeNum,conflictNum,neutralNum
	
	
	
	
	

def textExtract():		
	termList=[[]]; categoryList=[]; polarityList=[]
	from xml.etree import ElementTree as ET
	per = ET.parse('C:\Users\hxj\Desktop\Restaurants_Train.xml')
	p = per.findall('./sentence')
	for oneper in p:
		termL=[]
		categoryL=[]
		categoryLi=[]
		polarityL=[]
		polarityLi=[]
		for child in oneper.getchildren():
			
			if child.tag == 'text':
				wordList = child.text
				termL = textParse(wordList)
				#print termL
				#print wordList
#			if child.tag == 'aspectTerms':
#				q = child.findall('./aspectTerm')
				#termLi=[]
#				for twoper in q:
#					termValue = twoper.get("term")
#					polarityValue1 = twoper.get("polarity")
					#print termValue
#					termL.append(termValue)
				#print termL
					#print twoper.get("term")
					#print twoper.get("polarity")
			if child.tag == 'aspectCategories':
				o = child.findall('./aspectCategory')
				
				for threeper in o:
					categoryValue = threeper.get("category")
					polarityValue2 = threeper.get("polarity")
					categoryL.append(categoryValue)
					polarityL.append(polarityValue2)
					#print threeper.get("category")
					#print threeper.get("polarity")
		#print termL
		termList.append(termL)
		#print categoryL[0]
		
		if categoryL[0] == 'food':
			categoryLi=0
		elif categoryL[0] == 'service':
			categoryLi=1
		elif categoryL[0] == 'price':
			categoryLi=2
		elif categoryL[0] == 'ambience':
			categoryLi=3
		elif categoryL[0] == 'anecdotes/miscellaneous':
			categoryLi=4
		
		if polarityL[0] == 'positive':
			polarityLi=0
		elif polarityL[0] == 'negative':
			polarityLi=1
		elif polarityL[0] == 'conflict':
			polarityLi=2
		elif polarityL[0] == 'neutral':
			polarityLi=3
		
		categoryList.append(categoryLi)
		polarityList.append(polarityLi)
	myVocabList1 = createVocabList(termList)
	#print termList
	#print categoryList
	
	return termList, categoryList, polarityList
	
					
		


	

def classifyNB(vec2Classify, p0Vec, p1Vec, p2Vec, p3Vec, p4Vec, pClass0, pClass1, pClass2, pClass3, pClass4):
	p1 = sum(vec2Classify * p1Vec) + log(pClass1)
	p2 = sum(vec2Classify * p2Vec) + log(pClass2)
	p3 = sum(vec2Classify * p3Vec) + log(pClass3)
	p4 = sum(vec2Classify * p4Vec) + log(pClass4)
	p0 = sum(vec2Classify * p0Vec) + log(pClass0)
	
	#print p1,p2,p3,p4,p0
	max = p1
	flag = 'service'
	if max<p2:
		max=p2
		flag='price'
	if max<p3:
		max=p3
		flag='ambience'
	if max<p4:
		max=p4
		flag='anecdotes/miscellaneous'
	if max<p0:
		max=p0
		flag='food'
	return flag
	
	
def classifyNB1(vec2Classify, p0Vec, p1Vec, p2Vec, p3Vec, pClass0, pClass1, pClass2, pClass3):
	p1 = sum(vec2Classify * p1Vec) + log(pClass1)
	p2 = sum(vec2Classify * p2Vec) + log(pClass2)
	p3 = sum(vec2Classify * p3Vec) + log(pClass3)
	p0 = sum(vec2Classify * p0Vec) + log(pClass0)
	
	#print p1,p2,p3,p4,p0
	max = p0
	flag = 'positive'
	if max<p1:
		max=p1
		flag='negative'
	if max<p2:
		max=p2
		flag='conflict'
	if max<p3:
		max=p3
		flag='neutral'
	return flag
	
	
def testingNB():
	listOPosts,listClasses,polarityClasses = textExtract()
	myVocabList = createVocabList(listOPosts)
	trainMat=[]
	for postinDoc in listOPosts:
		trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
	p0Vect,p1Vect,p2Vect,p3Vect,p4Vect,foodNum,serviceNum,priceNum,ambienceNum,amecdoteNum = trainNB0(trainMat, listClasses)
	
	p0V,p1V,p2V,p3V,positiveNum,negativeNum,conflictNum,neutralNum = trainNB1(trainMat, polarityClasses)
	
	
	from xml.dom.minidom import Document
	
	doc = Document()
	sentences = doc.createElement('sentences') 
	#sentences.setAttribute('xsi:noNamespaceSchemaLocation','SemEvalSchema.xsd')
	doc.appendChild(sentences)
	
	
	
	from xml.etree import ElementTree as ET
	per = ET.parse('C:\Users\hxj\Desktop\Restaurants_Test_PhaseA.xml')
	p = per.findall('./sentence')
	for oneper in p:
		sentence = doc.createElement('sentence')
		sentences.appendChild(sentence)	
		for child in oneper.getchildren():
			if child.tag == 'text':
				wordList = child.text
				testEntry = textParse(wordList)
				#print testEntry
				thisDoc = array(setOfWords2Vec(myVocabList, testEntry))
				categoryC = classifyNB(thisDoc, p0Vect,p1Vect,p2Vect,p3Vect,p4Vect,foodNum,serviceNum,priceNum,ambienceNum,amecdoteNum)	
				polarityC = classifyNB1(thisDoc,p0V,p1V,p2V,p3V,positiveNum,negativeNum,conflictNum,neutralNum)
				
				text = doc.createElement('text')
				text_text = doc.createTextNode(wordList)
				text.appendChild(text_text)
				sentence.appendChild(text)
				
				aspectCategories = doc.createElement('aspectCategories')
				sentence.appendChild(aspectCategories)
				aspectCategory = doc.createElement('aspectCategory')
				aspectCategory.setAttribute('category',categoryC)
				aspectCategory.setAttribute('polarity',polarityC)
				aspectCategories.appendChild(aspectCategory)
				sentence.appendChild(aspectCategories)
				
	f = open(r'E:\FilePy\test11.xml','w')
	f.write(doc.toprettyxml(indent = ''))
	f.close()
	
	 
	

	