# -*- coding: utf-8 -*-
"""
Created on Wed Nov  4 19:31:24 2020

@author: eiahb
"""


import sys
from multiprocessing import Pool
import time


def mapRecord():
    # while(True):
    # if sys.stdin is not None:
    aRecord = line.split(",")          
    stockTimeStamp = "{}_{}".format(aRecord[0], aRecord[1][:12])
    print("%s\t%s" % (stockTimeStamp, aRecord[2]))
    
def main():
    # 读入每行input
    with Pool(16) as pool:
        iter(sys.stdin.readline, '')
        pool.close()
        pool.join()
        
if __name__ =="__main__":
    tic = time.time()
    main()
    toc = time.time() - tic
    print(toc)
    
    
    
    