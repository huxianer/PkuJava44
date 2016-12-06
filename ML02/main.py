#coding=utf-8
from parser import PreHandle
import re
import os
from urlparse import *

def main_function():
    pre = PreHandle()
    name = "James_Hamilton"

    if os.path.isdir(name + "_tmp"):
        print "the dir exists"
    else :
        os.mkdir(name + "_tmp")


    path = "test/web_pages/"+ name +"/" +name +".xml"
    # path = "test/description_files/Alvin_Cooper.xml"
    i = 0
    for i in range(0, 100):
        print i
        num_ = num(i)
        print str(num_)
        path_ = "test/web_pages/"+name+"/raw/"+ num_ + "/index.html"
        html = open(path_, "r")
        content = html.read()
        content = replaceCharEntity(filterTags(content))

        output = PreHandle.parseForTrainHtml(pre, content)
        url, title , snippet = PreHandle.parseForTrainXml(pre, path, i)

        # r = urlparse(url)
        # url = str(r.path).replace('/', ' ').replace(".html", " ")


        f = open(name + '_tmp/'+ num_ + '.txt', 'w')
        f.write(title.decode('iso-8859-1').encode('utf-8') + "."
                + snippet.decode('iso-8859-1').encode('utf-8') + ".")
        f.write(output.decode('iso-8859-1').encode('utf-8'))
        html.close()


    # output_ = PreHandle.parseForTrainXml(pre, path)
    # f.write(output_)


def num(num):
    if num >= 0 and num < 10:
        return "00" + str(num)
    if num >= 10 and num < 100:
        return "0" + str(num)
    else:
        return str(num)


def filterTags(htmlstr):
    # 先过滤CDATA
    re_cdata = re.compile('//<!\[CDATA\[[^>]*//\]\]>', re.I)  # 匹配CDATA
    re_script = re.compile('<\s*script[^>]*>[^<]*<\s*/\s*script\s*>', re.I)  # Script
    re_style = re.compile('<\s*style[^>]*>[^<]*<\s*/\s*style\s*>', re.I)  # style
    re_br = re.compile('<br\s*?/?>')  # 处理换行
    re_h = re.compile('</?\w+[^>]*>')  # HTML标签
    re_comment = re.compile('<![^>]*>')  # HTML注释
    # s = re_cdata.sub('', htmlstr)  # 去掉CDATA
    s = htmlstr
    s = re_script.sub('', s)  # 去掉SCRIPT
    s = re_style.sub('', s)  # 去掉style
    # s = re_br.sub('\n', s)  # 将br转换为换行
    # s=re_h.sub('',s) #去掉HTML 标签
    s = re_comment.sub('', s)  # 去掉HTML注释
    # 去掉多余的空行
    blank_line = re.compile('\n+')
    s = blank_line.sub('\n', s)
    s = replaceCharEntity(s)  # 替换实体
    return s

    ##替换常用HTML字符实体.
    # 使用正常的字符替换HTML中特殊的字符实体.
    # 你可以添加新的实体字符到CHAR_ENTITIES中,处理更多HTML字符实体.
    # @param htmlstr HTML字符串.


def replaceCharEntity(htmlstr):
    CHAR_ENTITIES = {'nbsp': ' ', '160': ' ',
                     'lt': '<', '60': '<',
                     'gt': '>', '62': '>',
                     'amp': '&', '38': '&',
                     'quot': '"', '34': '"',}

    re_charEntity = re.compile(r'&#?(?P<name>\w+);')
    sz = re_charEntity.search(htmlstr)
    while sz:
        entity = sz.group()  # entity全称，如&gt;
        key = sz.group('name')  # 去除&;后entity,如&gt;为gt
        try:
            htmlstr = re_charEntity.sub(CHAR_ENTITIES[key], htmlstr, 1)
            sz = re_charEntity.search(htmlstr)
        except KeyError:
            # 以空串代替
            htmlstr = re_charEntity.sub('', htmlstr, 1)
            sz = re_charEntity.search(htmlstr)
    return htmlstr


main_function()