“为什么硬件cache line不做成一个字节？”

会导致硬件成本的上升，因为原本8个字节对应一个tag，现在需要8个tag，占用了很多内存。tag也是cache的一部分，但是我们谈到cache size的时候并不考虑tag占用的内存部分。
![缓存](image/缓存示意图.webp)

https://www.scss.tcd.ie/Jeremy.Jones/VivioJS/caches/MESIHelp.htm
