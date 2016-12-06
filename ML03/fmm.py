﻿#! /usr/bin/env python
# -*- coding: utf-8 -*-
   
    
import codecs
import sys
    
# 由规则处理的一些特殊符号
numMath = [u'0', u'1', u'2', u'3', u'4', u'5', u'6', u'7', u'8', u'9']
numMath_suffix = [u'.', u'%', u'亿', u'万', u'千', u'百', u'十', u'个']
numCn = [u'一', u'二', u'三', u'四', u'五', u'六', u'七', u'八', u'九', u'〇', u'零']
numCn_suffix_date = [u'年', u'月', u'日']
numCn_suffix_unit = [u'亿', u'万', u'千', u'百', u'十', u'个']
special_char = [u'(', u')']
    
    
def proc_num_math(line, start):
    """ 处理句子中出现的数学符号 """
    oldstart = start
    while line[start] in numMath or line[start] in numMath_suffix:
        start = start + 1
    if line[start] in numCn_suffix_date:
        start = start + 1
    return start - oldstart
    
def proc_num_cn(line, start):
    """ 处理句子中出现的中文数字 """
    oldstart = start
    while line[start] in numCn or line[start] in numCn_suffix_unit:
        start = start + 1
    if line[start] in numCn_suffix_date:
        start = start + 1
    return start - oldstart
    
def rules(line, start):
    """ 处理特殊规则 """
    if line[start] in numMath:
        return proc_num_math(line, start)
    elif line[start] in numCn:
        return proc_num_cn(line, start)
    
def genDict(path):
    """ 获取词典 """
    f = codecs.open(path,'r','utf-8')
    contents = f.read()
    contents = contents.replace(u'\r', u'')
    contents = contents.replace(u'\n', u'')
    # 将文件内容按空格分开
    mydict = contents.split(u' ')
    # 去除词典List中的重复
    newdict = list(set(mydict))
    newdict.remove(u'')
    
    # 建立词典
    # key为词首字，value为以此字开始的词构成的List
    truedict = {}
    for item in newdict:
        if len(item)>0 and item[0] in truedict:
            value = truedict[item[0]]
            value.append(item)
            truedict[item[0]] = value
        else:
            truedict[item[0]] = [item]
    return truedict
    
def print_unicode_list(uni_list):
    for item in uni_list:
        print item,
    
def divideWords(mydict, sentence):

    ruleChar = []
    ruleChar.extend(numCn)
    ruleChar.extend(numMath)
    result = []
    start = 0
    senlen = len(sentence)
    while start < senlen:
        curword = sentence[start]
        maxlen = 1
        # 首先查看是否可以匹配特殊规则
        if curword in numCn or curword in numMath:
            maxlen = rules(sentence, start)
        # 寻找以当前字开头的最长词
        if curword in mydict:
            words = mydict[curword]
            for item in words:
                itemlen = len(item)
                if sentence[start:start+itemlen] == item and itemlen > maxlen:
                    maxlen = itemlen
        result.append(sentence[start:start+maxlen])
        start = start + maxlen
    return result
    
def main():
    args = sys.argv[1:]
    if len(args) < 3:
        print 'Usage: python dw.py dict_path test_path result_path'
        exit(-1)
    dict_path = args[0]
    test_path = args[1]
    result_path = args[2]
    
    dicts = genDict(dict_path)
    fr = codecs.open(test_path,'r','utf-8')
    test = fr.read()
    result = divideWords(dicts,test)
    fr.close()
    fw = codecs.open(result_path,'w','utf-8')
    for item in result:
        fw.write(item + ' ')
    fw.close()
    
if __name__ == "__main__":
    main()