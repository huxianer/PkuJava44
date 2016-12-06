#coding=utf-8
import numpy as np
from sklearn.cluster import AffinityPropagation
from sklearn import cluster



def test():
    name = "Abby_Watkins"
    line = []

    for i in range(0, 3):
        num_ = num(i)
        f = open(name + '/' + num_ + '.txt', 'r')
        content = f.read().strip()
        items = content.split(" ")
        line_one = []
        for item in items:
            line_one.append(float(item))

        line.append(line_one)


    print line
    affinity(line)



def affinity(X_iris):
    affinity = cluster.AffinityPropagation(preference=-50)
    affinity.fit(X_iris)
    res = affinity.labels_

    print "res"
    print res
    return res

def num(num):
    if num >= 0 and num < 10:
        return "00" + str(num)
    if num >= 10 and num < 100:
        return "0" + str(num)
    else:
        return str(num)

test()