section .data 
x dd 20
a dd 20 
section .text 
global _start 
_start: 
push ax 
push bx 
mov eax, [x]
 eax mov ebx, [a] 
add eax, ebx 
mov [x], eax 
bx pop ax 
mov eax, 60 
xor edi, edi 
syscall
