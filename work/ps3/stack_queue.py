from collections import deque

class StackQueue(object):
	q1 = deque([])
	q2 = deque([])
	
	num_items = 0
	
	def push(self, x):
		while len(self.q1) > 0:
			self.q2.append(self.q1.popleft())
			print self.q1
		self.q1.append(x)
		while len(self.q2) > 0:
			self.q1.append(self.q2.popleft())
		self.num_items += 1	
		
		
			
	def pop(self):
		self.q1.popleft()
		self.num_items -= 1	
		
		
s = StackQueue()
s.push(1)
s.push(2)
print s.pop()
s.push(3)
s.push(4)
while s.num_items > 0:
	print s.pop()		
	
'''
expected output:
2
4
3
1
'''	