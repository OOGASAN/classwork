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

def is_square(apositiveint):
	if apositiveint > 0:
		x = apositiveint // 2
		seen = set([x])
		while x * x != apositiveint:
			x = (x + (apositiveint // x)) // 2
			if x in seen: return False
			seen.add(x)
		return True

def squares_less_than(n):
	squares = []
	while n > 1:
		if is_square(n):
			squares.append(n)
		n -= 1
	return squares
	


#def squares_less_than(n):
#	# 1 is only perfect square lower than four, so just return 1 and quit
#	if n < 4:
#		squares.append(1)
#		return True
#		 
#	else:
#		temp = largest_square(n)
#		squares.append(temp)
#		temp -= 1
#		squares_less_than(temp)

#squares =  squares_less_than(140)
#squares.append(1)		
#print squares
#print squares

def find_sum(n,i,TEMP):
	if TEMP > 0:
		print n
		if sum(terms) == n and i <= 4:
			print 'success! %s' % terms
			return True
			
		# fill squares w values
		squares = squares_less_than(n)
		if len(squares) > 1:
			squares.append(1)
		for j,val in enumerate(squares):
			print '%s: %s' % (n,squares)
			if j < 4:
				terms.append(val)
				if find_sum(n-largest_square(n), j+1,TEMP-1):
					print 'good terms so far: %s' % terms
					return true
				terms.remove(val)
		print 'bad terms: %s' % terms	
		return False
	
find_sum(140,0,4)
		
		
		
		
		
		
		
		
