
from PrePolarity import *
import xml.dom.minidom
from numpy import *

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
	myVocabList=[]
	trainWeightList=[]
	classSum = 5
	listOPosts,listClasses = textExtract()
	
	stop = stopwords.words('english')
	myList = createVocabList(listOPosts)
	
	for i in myList:
		if i not in stop:
			myVocabList.append(i)
	
	
	trainMat=[]
	cateFL=['food', 'service', 'price', 'ambience', 'anecdotes/miscellaneous']
	for postinDoc in listOPosts:
		trainMat.append(bagOfWords2VecMN(myVocabList, postinDoc))
	
	for i in range(classSum):
		trainW = stocGradAscent1(array(trainMat), listClasses[i], 200)
		trainWeightList.append(trainW)
	
	from xml.dom.minidom import Document
	
	doc = Document()
	sentences = doc.createElement('sentences') 
	doc.appendChild(sentences)

	from xml.etree import ElementTree as ET
	per = ET.parse('C:\Users\hxj\Desktop\Restaurants_Test_PhaseA.xml')
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
				thisDoc = bagOfWords2VecMN(myVocabList, testEntry)
				
				for iii in range(classSum):
					temp0,prob0 = classifyVector(array(thisDoc), trainWeightList[iii])
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
				
	f = open(r'E:\FilePy\test22.xml','w')
	f.write(doc.toprettyxml(indent = ''))
	f.close()
