export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)
$LLVM_TOOLCHAIN/gcc -I/usr/local/include -L/usr/local/lib ./src/main/cpp/hello.c -lpng -o hello
$LLVM_TOOLCHAIN/gcc -I/usr/local/include -L/usr/local/lib ./src/main/cpp/example.c -lpng -o example


mv hello ./src/main/resources/
