
# 运行方式1：直接运行main方法方式

1. main方法用了一个例子中的数据，
2. 直接在Idea中右键GetSnapshot类的main方法运行即可

```
historyData如下：
e4e87cb2-8e9a-4749-abb6-26c59344dfee 2016/09/02 22:30:46
cat1 10 9
351055db-33e6-4f9b-bfe1-16f1ac446ac1 2016/09/02 22:30:52
cat1 10 9 2 -1
cat2 2 3
dcfa0c7a-5855-4ed2-bc8c-4accae8bd155 2016/09/02 22:31:02
cat1 12 8 3 4

id: dcfa0c7a-5855-4ed2-bc8c-4accae8bd155

输出结果：
cat1 15 12
cat2 2 3
```

# 运行方式2：运行单元测试
1. 如果运行单元测试，需要引入JUnit4的jar包；
2. 单元测试谢了三个case
3. 直接在GetSnapshotTest上右键run GetSnapshotTest

case1：运行正确的case，数据用的跟mian形式运行的数据一样； 

case2:
是一个historyData Invalid format. 的例子
  
case3: 是一个Conflict 的例子

