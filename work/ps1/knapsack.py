items = [11, 8, 7, 6, 5]

knapsack = []

def find_solution(n,i):
#	print "target weight: %s checking: %s" % (n,items[i]) 
	if items[i] == n:
		knapsack.append(items[i])
#		print "%s  // Target = %s ,%s is just right" % (knapsack, n, items[i])
		print knapsack
		return True
		
	for val in items[i:]:
		if val < n:
			knapsack.append(val)
			
			if find_solution(n-val, i+1):
#				print "%s  // Target = %s ,%s is too small1" % (knapsack, n, items[i])
					
				return True
				
#			print "%s  // Target = %s ,%s is too small2" % (knapsack, n, items[i])
			knapsack.remove(val)
			
#	print "%s  // Target = %s ,%s is too big" % (knapsack, n, items[i])

	return False
	
find_solution(20, 0)