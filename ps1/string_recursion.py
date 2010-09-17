#def print_reverse(string):
##	print string
#	if string == "" or len(string) == 0:
#		return string
#	else:
#		bkwds = ''
#		for i in range(len(string)):
#			bkwds += string[(len(string)-1) - i]
#			
##		print bkwds[1:]
#		
#		return print_reverse(bkwds[1:])
##		return print_reverse(bkwds[:-1])
#		
#print print_reverse("harvard")


def print_reverse(string):
#	print string
	if string == "" or len(string) == 0:
		return string
	else:
#		print string
		newstring = print_reverse(string[1])
		print newstring
		bkwds = ''
		for i in range(len(newstring)):
			bkwds += string[(len(newstring)-1) - i]
			
		print bkwds
		
		return bkwds
#		return print_reverse(bkwds[:-1])
		
print print_reverse("harvard")

#def print_reverse(string):
#	if string == "" or len(string) == 0:
#		return string
#	else:
#		print string
#		outer_swapped = ''
#		newstring = print_reverse(string[1:])
#		if len(newstring) > 2:
#			outer_swapped = newstring[-1]
#			print outer_swapped
#			outer_swapped += newstring[1:-1]
#			print outer_swapped
#			outer_swapped += newstring[0]
#			outer_swapped = string
#		
#		return outer_swapped
##		return print_reverse(bkwds[:-1])
#		
#print print_reverse("harvard")