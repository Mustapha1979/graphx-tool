
# Tool to calculate partitioning metrics using GraphX

## Requirements:
### start spark cluster
### set SPARK_HOME

## run this command to compute graph's metrics

`./run.sh path/to/graph.txt metrics.txt RandomVertexCut 10 youtube`

`./run.sh <pathToGrpah> <filenameWithResult> <parititonerName> <minEdgePartitions> <shortGraphName>`

## <pathToGrpah> - path to graph
## <filenameWithResult> - path to the output file with metrics values
## <parititonerName> - name of the partitioner
## <minEdgePartitions> - number of edge partitions
## <shortGraphName> - short name of the graph

