def trim(string):
	if string[0] and string [-1] != " ":
		return string
	else:
		if string[0] == " ":
			string = string[1:]
		if string[-1] == " ":
			string = string[:-1]
		return trim(string)
		
print trim("  trim me  ")
print trim(" trim me  ")
print trim("trim me ")
print trim("trim") 
print trim("t")