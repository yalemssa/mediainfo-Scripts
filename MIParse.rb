require 'rexml/document'
require 'Nokogiri'

f = File.open ARGV[0]# '~/Documents/brbl_wa_mss_S-2636_R1.mp4'
path = File.expand_path f
cmd = "mediainfo -f --output=XML #{path}"
map = Hash.new
doc = REXML::Document.new(`#{cmd}`)

doc.elements.each("*/File/track") do |x|
  stream = Hash.new
  x.elements.each do |y|
    if !stream.has_key? y.name
      stream[y.name] = y.text
    end
  end
  stream.sort
  x.attributes['type'] == 'General' ? map[0] = stream : map[stream['ID']] = stream 
end

builder = Nokogiri::HTML::Builder.new do |doc|
  doc.html{
    doc.head{
      doc.link(:href => "blueprint/print.css", :rel => "stylesheet",:type => "text/css")
      doc.link(:href => "blueprint/screen.css", :rel => "stylesheet",:type => "text/css")  
    }
    doc.body{
      doc.div(:class => "container"){
        doc.h2 File.basename(path)
        doc.p "path: " << path
        map.each do |x|
          doc.h3 "#{x[0]}: #{x[1]['Kind_of_stream']}"
          doc.table{
            doc.thead{
              doc.tr{
                doc.th "field"
                doc.th "value"
              }
            }
            doc.tbody{
              x[1].keys.sort.each do |y|
                doc.tr{
                  doc.td y
                  doc.td x[1][y]
                }
              end
            }
          }
        end
      }
    }
  }
end

puts builder.to_html

