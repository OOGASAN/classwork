import math

#squares = []
terms = []

def largest_square(n):
	temp = int(math.sqrt(n))
	return temp * temp
	
# version 1, just return squares
#def find_sum(n):
#	if n == 1:
#		print terms
#		return
#	else:
#		square = largest_square(n)
#		terms.append(square)
#		find_sum(n - square)
#	   

def squares_less_than(n):
	squares = []
	print squares
	# 1 is only perfect square lower than four, so just return 1 and quit
	if n < 4:
		squares.append(1)
		return squares
		 
	else:
		temp = largest_square(n)
		squares.append(temp)
		temp -= 1
		squares_less_than(temp)
		
print squares_less_than(140)


#def find_sum(n,i,TEMP):
#	if TEMP > 0:
#		print n
#		if sum(terms) == n and i <= 4:
#			print terms
#			return True
#			
#		squares_less_than(n)
#		for j,val in enumerate(squares):
#			if j < 4:
#				terms.append(val)
#				if find_sum(n-largest_square(n), j+1,TEMP-1):
#					return true
#				terms.remove(val)
#			
#		return False
	
#find_sum(140,0,4)
		
		
		
		
		
		
		
		