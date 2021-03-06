var accessLogs = sc.textFile("/data/spark/project/access/access.log.45.gz")
 
//Check whats in the RDD. Each record of accessLogs RDDs should be the line //from the files in folder
accessLogs.take(10)
 
//Keep only the lines which have IP
def containsIP(line:String):Boolean = return line matches "^([0-9\\.]+) .*$"
var ipaccesslogs = accessLogs.filter(containsIP)
 
//Extract only IP
def extractIP(line:String):(String) = {
	// Here we are using the regular expression for matching the strings with a certain pattern.
	// To understand more, please watch the video here: <TODO>
    val pattern = "^([0-9\\.]+) .*$".r
    val pattern(ip:String) = line
    return (ip.toString)
}
var ips = ipaccesslogs.map(line => (extractIP(line),1));
 
//Count
var ipcounts = ips.reduceByKey((a,b) => (a+b))
var ipcountsOrdered = ipcounts.sortBy(f => f._2, false);
ipcountsOrdered.take(10)
