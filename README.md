
# Tool to calculate partitioning metrics using GraphX

## Requirements:
### start spark cluster
### set SPARK_HOME

## run this command to compute graph's metrics

`./run.sh path/to/graph.txt metrics.txt RandomVertexCut 10 graph
`
## where `path/to/graph.txt` is path to the file where each line contains onde grapg edge: src and destication,
## 10 means partition in 10 parts
## graph is just short name
