def find_pairs(k,arr):
	for i, elem in enumerate(arr):
#		print i
#		print arr[i + 1:]
		for j in arr[i + 1:]:
#			print 'checking %s + %s' % (i,j)
			if elem + j == k:
				print '%s + %s = %s' % (elem,j,k)

arr = [10, 4, 7, 7, 8, 5, 15]
				
find_pairs(12, arr)
		
		
# bug: fix so this		
#def find_pairs_faster(k,arr):
#	
#	arr.sort()
#	print arr
#	
#	for i, elem in enumerate(arr[1:]):
#		sum = elem + arr[i]
#		print '%s + %s = %s' % (elem,arr[i],sum)
#		if sum > k:
#			return
#		elif sum == k:
#			print 'solution: %s + %s = %s' % (elem,arr[i],k)
			
#find_pairs_faster(12, arr)			