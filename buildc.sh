export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)
$LLVM_TOOLCHAIN/clang -shared ./src/main/cpp/hello.c -lpolyglot-mock -o hello
mv hello ./src/main/resources/
