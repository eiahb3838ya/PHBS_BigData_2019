cd C:\Users\eiahb\Documents\MyFiles\SchoolThing\PHBS_big_data_analysis\PHBS_BigData_2019\2020\mapreduceInPython

hadoop jar hadoop-streaming-3.1.4.jar -conf testConf.xml -file mapper.py -mapper "python mapper.py" -file reducer.py -reducer "python reducer.py" -input /projectData/tickData/201902 -output /group5/output20190221

type 20190102.csv | python mapper.py | sort | python reducer.py>a.txt

hadoop jar hadoop-streaming-3.1.4.jar -conf testConf.xml -mapper "python mapper.py" -reducer "python reducer.py" -input /projectData/tickData/201902 -output /group5/output20
