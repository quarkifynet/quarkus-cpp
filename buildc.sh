export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)
#$LLVM_TOOLCHAIN/clang -shared ./src/main/cpp/hello.c -lpolyglot-mock -o hello
$LLVM_TOOLCHAIN/gcc -I/usr/local/include -L/usr/local/lib ./src/main/cpp/hello.c -ltensorflow -o hello

mv hello ./src/main/resources/
