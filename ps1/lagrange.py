import math

terms = []

def largest_square(n):
	temp = int(math.sqrt(n))
	return temp * temp
	
# version 1, just return squares
def find_sum(n):
	if n == 1:
		return
	else:
		square = largest_square(n)
		terms.append(square)
		find_sum(n - square)
	   
find_sum(140)
print terms