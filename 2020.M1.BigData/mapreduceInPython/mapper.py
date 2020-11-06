# -*- coding: utf-8 -*-
"""
Created on Wed Nov  4 19:31:24 2020

@author: eiahb
"""


import sys
from multiprocessing import Pool
import time


def main():
    # 读入每行input
    for line in sys.stdin:
        aRecord = line.split(",")          
        stockTimeStamp = "{}_{}".format(aRecord[0], aRecord[1][:12])
        # results = []
        print("%s\t%s" % (stockTimeStamp,aRecord[2]))

        
if __name__ =="__main__":
    tic = time.time()
    main()
    toc = time.time() - tic
  
    
    
    
    