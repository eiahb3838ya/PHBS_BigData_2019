cd C:\Users\eiahb\Documents\MyFiles\SchoolThing\PHBS_big_data_analysis\mapreduceInPython
type 20190102.csv | python mapper.py | sort | python reducer.py>a.txt
hadoop jar hadoop-streaming-3.1.4.jar -file mapper.py -mapper "python mapper.py" -file reducer.py -reducer "python reducer.py" -input /projectData/tickData/201902 -output /group5/output
