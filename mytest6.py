#coding=utf-8
import poplib, getpass, string, sys  
import codecs 
import base64 
import re
import mytest11
from mytest11 import mdcode
import quopri
#host = raw_input('Input your mail hostname [default sina]:')  
# if (len(host)) == 0:  
    # host = 'pop.sina.cn'  
  
#username = raw_input('username:')  
#password = getpass.getpass() 
username="1179350451" 
password="qwert12345"     
try:  
    #session = poplib.POP3_SSL(host)
    session = poplib.POP3_SSL("pop.qq.com")
    session.user(username)  
    session.pass_(password)  
except:  
    print 'Something was wrong, go to check it'  
    sys.exit()  
      
#mailInfo = session.stat()
mailInfo = len(session.list()[1])  
#print 'mail number:',session.list()  
#print 'mail length:',mailInfo[1]/1024,'KB'  
  
#type = sys.getfilesystemencoding()  
#print type  
f = open("test_qq_com.txt", "w") 
# for i in range(1,mailInfo[0]+1):      
    # #print i,'-->'  
    # for s in session.top(i,0)[1]:  
for i in range(mailInfo):
    for s in session.retr(i+1)[1]:
        # if s[0:5] == 'From:':  
            # print s  
        # elif s[0:3] == 'To:':  
            # print s  
        # elif s[0:5] == 'Date:':  
            # print s  
        # elif s[0:8] == 'Subject:':  
            # print s  
    # print '===========================\n' 
        char1='B'
        char2='Q'
        if s[0:5] == 'From:':
            # l=[]
            # tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            # char='B'
            # if len(tmp)>0:
                # l=list(tmp[0])
                # #print list(tmp[0])
            # #print l
                # if l[1] is char:
                    # lens = len(s)
                    # lenx = lens - (lens % 4 if lens % 4 else 4)
                    # try:
                        # result = base64.decodestring(s[:lenx])
                    # except:
                        # pass
                    # f.write(result.decode(l[0]))
                # else:
                    # f.write(s.decode("iso-8859-1").encode(l[0]))
            # f.write('\n')
            # tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            # char='B'
            # #print tmp
            # if tmp is char:
                # f.write(base64.decodestring(s).encode("gbk"))
            # else:
                # f.write(s.decode("iso-8859-1").encode("gb18030"))
            # f.write('\n')
            
            l=[]
            tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            char='B'
            if len(tmp)>0:
                l=list(tmp[0])
                # print list(tmp[0])
                # print l
                if l[1] is char1:
                    sgbk=base64.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                elif l[1] is char2:
                    sgbk=quopri.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                else:
                    f.write(s.decode("iso-8859-1").encode("gbk"))
                f.write('\n')
        elif s[0:3] == 'To:':  
            l=[]
            tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            char='B'
            if len(tmp)>0:
                l=list(tmp[0])
                # print list(tmp[0])
                # print l
                if l[1] is char1:
                    sgbk=base64.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                elif l[1] is char2:
                    sgbk=quopri.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                else:
                    f.write(s.decode("iso-8859-1").encode("gbk"))
                f.write('\n')
        elif s[0:5] == 'Date:': 
            l=[]
            tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            char='B'
            if len(tmp)>0:
                l=list(tmp[0])
                # print list(tmp[0])
                # print l
                if l[1] is char1:
                    sgbk=base64.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                elif l[1] is char2:
                    sgbk=quopri.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                else:
                    f.write(s.decode("iso-8859-1").encode("gbk"))
                f.write('\n')
        elif s[0:8] == 'Subject:': 
            l=[]
            tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            char='B'
            if len(tmp)>0:
                l=list(tmp[0])
                # print list(tmp[0])
                # print l
                if l[1] is char1:
                    sgbk=base64.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                elif l[1] is char2:
                    sgbk=quopri.decodestring(l[2])
                    f.write(mdcode(sgbk,'gbk'))
                else:
                    f.write(s.decode("iso-8859-1").encode("gbk"))
                f.write('\n')
        elif s[0:26] == 'Content-Transfer-Encoding:': 
            # l=[]
            # tmp=re.findall(r'\?(.+?)\?(.+?)\?(.+?)\?',s)
            # char='B'
            # if len(tmp)>0:
                # l=list(tmp[0])
                # # print list(tmp[0])
                # # print l
                # if l[1] is char1:
                print s
                #sgbk=base64.decodestring(s.replace("base64",""))
                f.write(s)
                # elif l[1] is char2:
                    # sgbk=quopri.decodestring(l[2])
                    # f.write(mdcode(sgbk,'gbk'))
                # else:
                    # f.write(s.decode("iso-8859-1").encode("gbk"))
                f.write('\n')
    f.write('-'*50)
    f.write('\n') 
     #f.write(s)    
session.quit()  
