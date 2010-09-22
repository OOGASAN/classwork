import math

squares = []
terms = []

def largest_square(n):
	temp = int(math.sqrt(n))
	return temp * temp
	
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

ORIG_NUM = 7
squares_less_than(ORIG_NUM)
#squares.append(1)		

def find_sum(n,i):

#	print n
	if sum(terms) == ORIG_NUM and i <= 4:
		print 'success! %s' % terms
		return True
		
	for val in squares[i:]:
		if len(terms) < 4:
			terms.append(val)
			if find_sum(n-squares[i], i):
#				print 'good terms so far: %s' % terms
				return True
			terms.remove(val)
#	print 'bad terms: %s' % terms	
	return False
	
find_sum(ORIG_NUM,0)
		
		
		
		
		
		
		
		
