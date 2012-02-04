import java.security.MessageDigest
File f = new File(args[0])

int KB = 1024
int MB = 1024*KB



def messageDigest = MessageDigest.getInstance("SHA1")

long start = System.currentTimeMillis()

f.eachByte(MB) { byte[] buf, int bytesRead ->
  messageDigest.update(buf, 0, bytesRead);
}

def sha1Hex = new BigInteger(1, messageDigest.digest()).toString(16).padLeft( 40, '0' )
long delta = System.currentTimeMillis()-start


def file = f.getAbsolutePath()
def info = "mediainfo -f --output=xml $file".execute().text
def streams = [:]

def records = new XmlParser().parseText(info)
def genFields = new TreeMap<String, String>()


records.File.track.each{
	def fields = new TreeMap<String, String>()
	it.each{x ->
		if(!fields.containsKey(x.name()))
			fields.putAt(x.name(), x.text())	
	}
	fields.sort()
	(fields.ID == null) ? streams.putAt(0, fields) : streams.putAt(fields.ID, fields)
}

print "<html><head><link href=\"blueprint/print.css\" rel=\"stylesheet\" type=\"text/css\"/><link href=\"blueprint/screen.css\" rel=\"stylesheet\" type=\"text/css\"/></head>"

print"<body><div class=\"container\">"
print"<h2>" + f.getName() + "</h2>"
print"<p><strong>SHA-1: </strong>$sha1Hex<br/><strong>path:</strong> " + f.getAbsolutePath() + "</strong><p>"

streams.each{
	print "<h3>$it.key : $it.value.Kind_of_stream</h3>"
	print"<table><thead><tr><th>field</th><th>value</th></tr><thead><tbody>"
	it.value.each{i ->
		println "<tr><td>$i.key</td><td>$i.value</td></tr>"
	}
	print"</tbody></table>"
	
}