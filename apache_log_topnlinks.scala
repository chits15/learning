val PATTERN = """^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] "(\S+) (\S+)(.*)" (\d{3}) (\S+)""".r

def containsIP(line:String):Boolean = return line matches "^([0-9\\.]+) .*$"
//Extract only IP
def extractIP(line:String):(String) = {
    val pattern = "^([0-9\\.]+) .*$".r
    val pattern(ip:String) = line
    return (ip.toString)
}

var accessLogs = sc.textFile(args(0))
//Keep only the lines which have IP
var ipaccesslogs = accessLogs.filter(containsIP)
var cleanips = ipaccesslogs.map(extractIP(_))
var ips_tuples = cleanips.map((_,1));

var sortedfrequencies = frequencies.sortBy(x => x._2, false)
var top10 = sortedfrequencies.take(topn)

println("===== TOP 10 IP Addresses =====")
for(i <- top10){
    println(i)
}