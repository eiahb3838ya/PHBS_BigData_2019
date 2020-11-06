# -*- coding: utf-8 -*-
"""
Created on Wed Nov  4 19:44:21 2020

@author: eiahb
"""


from operator import itemgetter
import sys
 
stockTime_dict = {}


for line in sys.stdin:
    line = line.strip()
    stockTimeStamp, price = line.split()
    try:
        price = float(price)
        if stockTimeStamp not in stockTime_dict:
            stockTime_dict[stockTimeStamp] = {'open':price}
        
        stockTime_dict[stockTimeStamp]['high'] = max(stockTime_dict[stockTimeStamp].get('high',0), price)
        stockTime_dict[stockTimeStamp]['low'] = min(stockTime_dict[stockTimeStamp].get('low',sys.maxsize), price)
        stockTime_dict[stockTimeStamp]['close'] = price
        
    except ValueError as err:
        print(err)
        pass
 
sorted_stockTime_dict = sorted(stockTime_dict.items(),key=itemgetter(0))
for stockTime, _dict in sorted_stockTime_dict:
    print("%s\t%f\t%f\t%f\t%f" % (stockTime, _dict['open'], _dict['high'], _dict['low'],_dict['close']))
