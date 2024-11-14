section .data 
N dd 5 

section .text 
global_start 

_start: 
mov eax, [N] 
EAX shl eax, 1 
mov [N], eax 
mov eax, 60 
xor edi, edi 
syscall
