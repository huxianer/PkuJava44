# -*- coding: UTF-8 -*-
from nltk.corpus import stopwords, wordnet
import xml.dom.minidom
import re
import sys
import formatter
from lineWritter import LineWriter
from trackingParser import TrackingParser
import urllib


reload(sys)
sys.setdefaultencoding('utf-8')

class PreHandle(formatter.AbstractWriter):

    def __init__(self):
        self.classes = []

    def parseForTrainXml(self, path, i):
        # 使用minidom解析器打开XML文档
        DOMTree = xml.dom.minidom.parse(path)
        # 读取sentence标签
        docs = DOMTree.documentElement.getElementsByTagName("doc")
        # 停用词
        stop = stopwords.words("english")

        for doc in docs:
            if int(doc.getAttribute("rank")) == i:
                url = doc.getAttribute("url")
                title = doc.getAttribute("title")
                snippet = doc.getElementsByTagName("snippet")
                print url, title,snippet

                return url,str(title), str(snippet)
        tmp = " "
        return tmp, tmp, tmp


    def parseForTrainHtml(self, html):
        # def extract_text(html):
        # Derive from formatter.AbstractWriter to store paragraphs.
        writer = LineWriter()
        # Default formatter sends commands to our writer.
        formatter_ = formatter.AbstractFormatter(writer)
        # Derive from htmllib.HTMLParser to track parsed bytes.
        parser = TrackingParser(writer, formatter_)
        # Give the parser the raw HTML data.
        parser.feed(html)
        parser.close()
        # Filter the paragraphs stored and output them.
        return writer.output()


