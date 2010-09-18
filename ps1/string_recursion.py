def reverse(string):
	if string == "" or len(string) == 1:
		return string
	else:
		
		return string[-1] + print_reverse(string[:-1])
		
print reverse("yard")