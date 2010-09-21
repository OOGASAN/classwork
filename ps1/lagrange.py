import math

squares = []
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

#def is_square(apositiveint):
#	if apositiveint > 0:
#		x = apositiveint // 2
#		seen = set([x])
#		while x * x != apositiveint:
#			x = (x + (apositiveint // x)) // 2
#			if x in seen: return False
#			seen.add(x)
#		return True
#
#def squares_less_than(n):
#	squares = []
#	while n > 1:
#		if is_square(n):
#			squares.append(n)
#		n -= 1
#	return squares
	


def squares_less_than(n):
	# 1 is only perfect square lower than four, so just return 1 and quit
	if n < 4:
		squares.append(1)
		return True
		 
	else:
		temp = largest_square(n)
		squares.append(temp)
		temp -= 1
		squares_less_than(temp)

ORIG_NUM = 11
squares_less_than(ORIG_NUM)
#squares.append(1)		

def find_sum(n,i,TEMP):
	if TEMP > 0:
		print n
		if sum(terms) == ORIG_NUM and i <= 4:
			print 'success! %s' % terms
			return True
			

		for val in squares[i:]:
			if len(terms) < 4:
				terms.append(val)
				if find_sum(n-squares[i], i,TEMP-1):
					print 'good terms so far: %s' % terms
					return True
				terms.remove(val)
		print 'bad terms: %s' % terms	
		return False
	
find_sum(ORIG_NUM,0,4)
		
		
		
		
		
		
		
		
