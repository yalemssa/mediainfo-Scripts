Description of Scripts:

<h3>MIParse.groovy</h3>
<p>Parses a media file with mediainfo (you must have this on your path) and returns a map of each underlying stream. Each entry in the map is another map that contains only the most machine readable forms of each field and has been sorted alphabetically. 
	
<emph>execute: >$groovy MIParse.groovy > output.html
	
<p>example output:<p>
	<h3>1 : Video</h3><table><thead><tr><th>field</th><th>value</th></tr><thead><tbody><tr><td>Bit_depth</td><td>8</td></tr>
	<tr><td>Bit_rate</td><td>1489686</td></tr>
	<tr><td>Bit_rate_mode</td><td>Variable</td></tr>
	<tr><td>Bits__Pixel_Frame_</td><td>0.162</td></tr>
	<tr><td>Chroma_subsampling</td><td>4:2:0</td></tr>
	<tr><td>Codec</td><td>AVC</td></tr>
	<tr><td>Codec_CC</td><td>avc1</td></tr>
	<tr><td>Codec_Family</td><td>AVC</td></tr>
	<tr><td>Codec_ID</td><td>avc1</td></tr>
	<tr><td>Codec_ID_Info</td><td>Advanced Video Coding</td></tr>
	<tr><td>Codec_ID_Url</td><td>http://www.apple.com/quicktime/download/standalone.html</td></tr>
	<tr><td>Codec_Info</td><td>Advanced Video Codec</td></tr>
	<tr><td>so on and so forth....</td><td/>
	</tbody></table>