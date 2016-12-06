def stripFile():
    
  '''''remove the space or Tab or enter in a file,and output to a new file in the same folder'''
  home_dir = "D:/Python27/"
  fp = open( home_dir + 'test_seg.data','r+')
  newFp = open( home_dir  + 'test_pre.data','w' )

  for eachline in fp.readlines():
    newStr = eachline.replace(" ","").strip()
    #print "Write:",newStr
    newFp.write(newStr)
  fp.close()
  newFp.close()
if __name__ == "__main__":
  stripFile()
  print "finish output to new file:"