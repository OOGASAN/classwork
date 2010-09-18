def reverse(string):
	if string == "" or len(string) == 1:
		return string
	else:	
		return string[-1] + reverse(string[:-1])
		
print reverse("yard")


def print_reverse(string):
	if len(string) == 1:
#		print 'basecase'
		print string
		return None
	else:
		print string[-1]
		return string[-1] + print_reverse(string[:-1])

print_reverse("yard")