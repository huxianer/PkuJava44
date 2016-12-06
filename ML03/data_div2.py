
# -*- coding: utf-8 -*-



import sys
  


home_dir = "D:/Python27/"
def splitWord(words):
    uni = words.decode('utf-8')
    li = list()    
    for u in uni:
	li.append(u.encode('utf-8'))
    return li

#S/B/E/M
def get4Tag(li):
    length = len(li)
    #print length
    if length   == 1:
	return ['S']
    elif length == 2:
	return ['B','E']
    elif length > 2:
	li = list()
	li.append('B')
	for i in range(0,length-2):
	    li.append('M')
	li.append('E')
	return li


def saveDataFile(trainobj,testobj,isTest,word):
    if isTest:
	saveTrainFile(testobj,word)
    else:
	saveTrainFile(trainobj,word)

def saveTrainFile(fiobj,word): 
    if len(word) > 0:
	wordli = splitWord(word)
	tagli = get4Tag(wordli)
	for i in range(0,len(wordli)):
		w = wordli[i]
		t = tagli[i]
		fiobj.write(w + "/"+t+" ")
    else:
	#print 'New line'
	fiobj.write('/n')

def convertTag():    
    fiobj    = open( home_dir + 'train_utf16.seg','r')
    trainobj = open( home_dir  + 'train.data','w' )
    testobj  = open( home_dir  + 'test.data','w')

    arr = fiobj.readlines()
    i = 0
    for a in arr:
	i += 1
	#a = a.strip('/r/n/t ')
	words = a.split(' ')
	test = False
	if i % 30 == 0:
	    test = True
	for word in words:
	    word = word.strip('/t ')
	    if len(word) > 0:		
		i1 = word.find('[')
		if i1 >= 0:
		    word = word[i1+1:]
		i2 = word.find(']')
		if i2 > 0:
		    word = word[:i2]

		saveDataFile(trainobj,testobj,test,word)

	trainobj.flush()
	testobj.flush()

if __name__ == '__main__':    
    if len(sys.argv) < 2:
	print 'tag[6,4] convert raw data to train.data and tag.test.data'
	convertTag()