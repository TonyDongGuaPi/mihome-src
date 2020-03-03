package org.libsodium.jni;

public class Sodium {
    public static int a() {
        return SodiumJNI.sodium_init();
    }

    public static byte[] b() {
        return SodiumJNI.sodium_version_string();
    }

    public static void a(byte[] bArr, int i) {
        SodiumJNI.randombytes(bArr, i);
    }

    public static int c() {
        return SodiumJNI.randombytes_random();
    }

    public static int a(int i) {
        return SodiumJNI.randombytes_uniform(i);
    }

    public static void b(byte[] bArr, int i) {
        SodiumJNI.randombytes_buf(bArr, i);
    }

    public static int d() {
        return SodiumJNI.randombytes_close();
    }

    public static void e() {
        SodiumJNI.randombytes_stir();
    }

    public static void c(byte[] bArr, int i) {
        SodiumJNI.sodium_increment(bArr, i);
    }

    public static int f() {
        return SodiumJNI.crypto_secretbox_keybytes();
    }

    public static int g() {
        return SodiumJNI.crypto_secretbox_noncebytes();
    }

    public static int h() {
        return SodiumJNI.crypto_secretbox_macbytes();
    }

    public static int i() {
        return SodiumJNI.crypto_secretbox_zerobytes();
    }

    public static int j() {
        return SodiumJNI.crypto_secretbox_boxzerobytes();
    }

    public static byte[] k() {
        return SodiumJNI.crypto_secretbox_primitive();
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_secretbox_easy(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_secretbox_open_easy(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_secretbox_detached(bArr, bArr2, bArr3, i, bArr4, bArr5);
    }

    public static int b(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_secretbox_open_detached(bArr, bArr2, bArr3, i, bArr4, bArr5);
    }

    public static int l() {
        return SodiumJNI.crypto_scalarmult_bytes();
    }

    public static int m() {
        return SodiumJNI.crypto_scalarmult_scalarbytes();
    }

    public static byte[] n() {
        return SodiumJNI.crypto_scalarmult_primitive();
    }

    public static int a(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_scalarmult_base(bArr, bArr2);
    }

    public static int a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_scalarmult(bArr, bArr2, bArr3);
    }

    public static int o() {
        return SodiumJNI.crypto_box_seedbytes();
    }

    public static int p() {
        return SodiumJNI.crypto_box_publickeybytes();
    }

    public static int q() {
        return SodiumJNI.crypto_box_secretkeybytes();
    }

    public static int r() {
        return SodiumJNI.crypto_box_noncebytes();
    }

    public static int s() {
        return SodiumJNI.crypto_box_macbytes();
    }

    public static byte[] t() {
        return SodiumJNI.crypto_box_primitive();
    }

    public static int b(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_box_keypair(bArr, bArr2);
    }

    public static int b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_box_seed_keypair(bArr, bArr2, bArr3);
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_easy(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_open_easy(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_box_detached(bArr, bArr2, bArr3, i, bArr4, bArr5, bArr6);
    }

    public static int b(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_box_open_detached(bArr, bArr2, bArr3, i, bArr4, bArr5, bArr6);
    }

    public static int u() {
        return SodiumJNI.crypto_box_beforenmbytes();
    }

    public static int c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_box_beforenm(bArr, bArr2, bArr3);
    }

    public static int c(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_easy_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int d(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_open_easy_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int c(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_detached_afternm(bArr, bArr2, bArr3, i, bArr4, bArr5);
    }

    public static int d(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_open_detached_afternm(bArr, bArr2, bArr3, i, bArr4, bArr5);
    }

    public static int v() {
        return SodiumJNI.crypto_box_sealbytes();
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_box_seal(bArr, bArr2, i, bArr3);
    }

    public static int e(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_seal_open(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int w() {
        return SodiumJNI.crypto_box_zerobytes();
    }

    public static int x() {
        return SodiumJNI.crypto_box_boxzerobytes();
    }

    public static int c(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int d(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_open(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int f(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int g(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_open_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int y() {
        return SodiumJNI.crypto_sign_bytes();
    }

    public static int z() {
        return SodiumJNI.crypto_sign_seedbytes();
    }

    public static int A() {
        return SodiumJNI.crypto_sign_publickeybytes();
    }

    public static int B() {
        return SodiumJNI.crypto_sign_secretkeybytes();
    }

    public static byte[] C() {
        return SodiumJNI.crypto_sign_primitive();
    }

    public static int c(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_keypair(bArr, bArr2);
    }

    public static int d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_sign_seed_keypair(bArr, bArr2, bArr3);
    }

    public static int a(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign(bArr, iArr, bArr2, i, bArr3);
    }

    public static int b(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_open(bArr, iArr, bArr2, i, bArr3);
    }

    public static int c(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_detached(bArr, iArr, bArr2, i, bArr3);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_verify_detached(bArr, bArr2, i, bArr3);
    }

    public static int d(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_ed25519_sk_to_seed(bArr, bArr2);
    }

    public static int e(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_ed25519_sk_to_pk(bArr, bArr2);
    }

    public static int D() {
        return SodiumJNI.crypto_generichash_bytes();
    }

    public static int E() {
        return SodiumJNI.crypto_generichash_bytes_min();
    }

    public static int F() {
        return SodiumJNI.crypto_generichash_bytes_max();
    }

    public static int G() {
        return SodiumJNI.crypto_generichash_keybytes();
    }

    public static int H() {
        return SodiumJNI.crypto_generichash_keybytes_min();
    }

    public static int I() {
        return SodiumJNI.crypto_generichash_keybytes_max();
    }

    public static byte[] J() {
        return SodiumJNI.crypto_generichash_primitive();
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        return SodiumJNI.crypto_generichash(bArr, i, bArr2, i2, bArr3, i3);
    }

    public static int K() {
        return SodiumJNI.crypto_generichash_statebytes();
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, int i2) {
        return SodiumJNI.crypto_generichash_init(bArr, bArr2, i, i2);
    }

    public static int a(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_generichash_update(bArr, bArr2, i);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_generichash_final(bArr, bArr2, i);
    }

    public static int L() {
        return SodiumJNI.crypto_shorthash_bytes();
    }

    public static int M() {
        return SodiumJNI.crypto_shorthash_keybytes();
    }

    public static byte[] N() {
        return SodiumJNI.crypto_shorthash_primitive();
    }

    public static int c(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_shorthash(bArr, bArr2, i, bArr3);
    }

    public static int O() {
        return SodiumJNI.crypto_auth_bytes();
    }

    public static int P() {
        return SodiumJNI.crypto_auth_keybytes();
    }

    public static byte[] Q() {
        return SodiumJNI.crypto_auth_primitive();
    }

    public static int d(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth(bArr, bArr2, i, bArr3);
    }

    public static int e(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_verify(bArr, bArr2, i, bArr3);
    }

    public static int R() {
        return SodiumJNI.crypto_onetimeauth_bytes();
    }

    public static int S() {
        return SodiumJNI.crypto_onetimeauth_keybytes();
    }

    public static byte[] T() {
        return SodiumJNI.crypto_onetimeauth_primitive();
    }

    public static int f(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_onetimeauth(bArr, bArr2, i, bArr3);
    }

    public static int g(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_onetimeauth_verify(bArr, bArr2, i, bArr3);
    }

    public static int U() {
        return SodiumJNI.crypto_onetimeauth_statebytes();
    }

    public static int f(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_onetimeauth_init(bArr, bArr2);
    }

    public static int c(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_onetimeauth_update(bArr, bArr2, i);
    }

    public static int g(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_onetimeauth_final(bArr, bArr2);
    }

    public static int V() {
        return SodiumJNI.crypto_aead_chacha20poly1305_keybytes();
    }

    public static int W() {
        return SodiumJNI.crypto_aead_chacha20poly1305_nsecbytes();
    }

    public static int X() {
        return SodiumJNI.crypto_aead_chacha20poly1305_npubbytes();
    }

    public static int Y() {
        return SodiumJNI.crypto_aead_chacha20poly1305_abytes();
    }

    public static int a(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_aead_chacha20poly1305_encrypt(bArr, iArr, bArr2, i, bArr3, i2, bArr4, bArr5, bArr6);
    }

    public static int a(byte[] bArr, int[] iArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_aead_chacha20poly1305_decrypt(bArr, iArr, bArr2, bArr3, i, bArr4, i2, bArr5, bArr6);
    }

    public static int Z() {
        return SodiumJNI.crypto_aead_chacha20poly1305_ietf_npubbytes();
    }

    public static int b(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_aead_chacha20poly1305_ietf_encrypt(bArr, iArr, bArr2, i, bArr3, i2, bArr4, bArr5, bArr6);
    }

    public static int b(byte[] bArr, int[] iArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, byte[] bArr5, byte[] bArr6) {
        return SodiumJNI.crypto_aead_chacha20poly1305_ietf_decrypt(bArr, iArr, bArr2, bArr3, i, bArr4, i2, bArr5, bArr6);
    }

    public static int aa() {
        return SodiumJNI.crypto_auth_hmacsha256_bytes();
    }

    public static int ab() {
        return SodiumJNI.crypto_auth_hmacsha256_keybytes();
    }

    public static int h(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha256(bArr, bArr2, i, bArr3);
    }

    public static int i(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha256_verify(bArr, bArr2, i, bArr3);
    }

    public static int ac() {
        return SodiumJNI.crypto_auth_hmacsha256_statebytes();
    }

    public static int d(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha256_init(bArr, bArr2, i);
    }

    public static int e(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha256_update(bArr, bArr2, i);
    }

    public static int h(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_auth_hmacsha256_final(bArr, bArr2);
    }

    public static int ad() {
        return SodiumJNI.crypto_auth_hmacsha512_bytes();
    }

    public static int ae() {
        return SodiumJNI.crypto_auth_hmacsha512_keybytes();
    }

    public static int j(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha512(bArr, bArr2, i, bArr3);
    }

    public static int k(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha512_verify(bArr, bArr2, i, bArr3);
    }

    public static int af() {
        return SodiumJNI.crypto_auth_hmacsha512_statebytes();
    }

    public static int f(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha512_init(bArr, bArr2, i);
    }

    public static int g(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha512_update(bArr, bArr2, i);
    }

    public static int i(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_auth_hmacsha512_final(bArr, bArr2);
    }

    public static int ag() {
        return SodiumJNI.crypto_auth_hmacsha512256_bytes();
    }

    public static int ah() {
        return SodiumJNI.crypto_auth_hmacsha512256_keybytes();
    }

    public static int l(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha512256(bArr, bArr2, i, bArr3);
    }

    public static int m(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_auth_hmacsha512256_verify(bArr, bArr2, i, bArr3);
    }

    public static int ai() {
        return SodiumJNI.crypto_auth_hmacsha512256_statebytes();
    }

    public static int h(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha512256_init(bArr, bArr2, i);
    }

    public static int i(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_auth_hmacsha512256_update(bArr, bArr2, i);
    }

    public static int j(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_auth_hmacsha512256_final(bArr, bArr2);
    }

    public static int aj() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_seedbytes();
    }

    public static int ak() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_publickeybytes();
    }

    public static int al() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_secretkeybytes();
    }

    public static int am() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_beforenmbytes();
    }

    public static int an() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_noncebytes();
    }

    public static int ao() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_zerobytes();
    }

    public static int ap() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_boxzerobytes();
    }

    public static int aq() {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_macbytes();
    }

    public static int e(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int f(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_open(bArr, bArr2, i, bArr3, bArr4, bArr5);
    }

    public static int e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_seed_keypair(bArr, bArr2, bArr3);
    }

    public static int k(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_keypair(bArr, bArr2);
    }

    public static int f(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_beforenm(bArr, bArr2, bArr3);
    }

    public static int h(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int i(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_box_curve25519xsalsa20poly1305_open_afternm(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int ar() {
        return SodiumJNI.crypto_core_hsalsa20_outputbytes();
    }

    public static int as() {
        return SodiumJNI.crypto_core_hsalsa20_inputbytes();
    }

    public static int at() {
        return SodiumJNI.crypto_core_hsalsa20_keybytes();
    }

    public static int au() {
        return SodiumJNI.crypto_core_hsalsa20_constbytes();
    }

    public static int a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_core_hsalsa20(bArr, bArr2, bArr3, bArr4);
    }

    public static int av() {
        return SodiumJNI.crypto_core_salsa20_outputbytes();
    }

    public static int aw() {
        return SodiumJNI.crypto_core_salsa20_inputbytes();
    }

    public static int ax() {
        return SodiumJNI.crypto_core_salsa20_keybytes();
    }

    public static int ay() {
        return SodiumJNI.crypto_core_salsa20_constbytes();
    }

    public static int b(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_core_salsa20(bArr, bArr2, bArr3, bArr4);
    }

    public static int az() {
        return SodiumJNI.crypto_generichash_blake2b_bytes_min();
    }

    public static int aA() {
        return SodiumJNI.crypto_generichash_blake2b_bytes_max();
    }

    public static int aB() {
        return SodiumJNI.crypto_generichash_blake2b_bytes();
    }

    public static int aC() {
        return SodiumJNI.crypto_generichash_blake2b_keybytes_min();
    }

    public static int aD() {
        return SodiumJNI.crypto_generichash_blake2b_keybytes_max();
    }

    public static int aE() {
        return SodiumJNI.crypto_generichash_blake2b_keybytes();
    }

    public static int aF() {
        return SodiumJNI.crypto_generichash_blake2b_saltbytes();
    }

    public static int aG() {
        return SodiumJNI.crypto_generichash_blake2b_personalbytes();
    }

    public static int b(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        return SodiumJNI.crypto_generichash_blake2b(bArr, i, bArr2, i2, bArr3, i3);
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, byte[] bArr4, byte[] bArr5) {
        return SodiumJNI.crypto_generichash_blake2b_salt_personal(bArr, i, bArr2, i2, bArr3, i3, bArr4, bArr5);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i, int i2) {
        return SodiumJNI.crypto_generichash_blake2b_init(bArr, bArr2, i, i2);
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_generichash_blake2b_init_salt_personal(bArr, bArr2, i, i2, bArr3, bArr4);
    }

    public static int j(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_generichash_blake2b_update(bArr, bArr2, i);
    }

    public static int k(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_generichash_blake2b_final(bArr, bArr2, i);
    }

    public static int aH() {
        return SodiumJNI.crypto_hash_sha256_bytes();
    }

    public static int l(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_hash_sha256(bArr, bArr2, i);
    }

    public static int aI() {
        return SodiumJNI.crypto_hash_sha256_statebytes();
    }

    public static int a(byte[] bArr) {
        return SodiumJNI.crypto_hash_sha256_init(bArr);
    }

    public static int m(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_hash_sha256_update(bArr, bArr2, i);
    }

    public static int l(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_hash_sha256_final(bArr, bArr2);
    }

    public static int aJ() {
        return SodiumJNI.crypto_hash_sha512_bytes();
    }

    public static int n(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_hash_sha512(bArr, bArr2, i);
    }

    public static int aK() {
        return SodiumJNI.crypto_hash_sha512_statebytes();
    }

    public static int b(byte[] bArr) {
        return SodiumJNI.crypto_hash_sha512_init(bArr);
    }

    public static int o(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_hash_sha512_update(bArr, bArr2, i);
    }

    public static int m(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_hash_sha512_final(bArr, bArr2);
    }

    public static int aL() {
        return SodiumJNI.crypto_onetimeauth_poly1305_bytes();
    }

    public static int aM() {
        return SodiumJNI.crypto_onetimeauth_poly1305_keybytes();
    }

    public static int n(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_onetimeauth_poly1305(bArr, bArr2, i, bArr3);
    }

    public static int o(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_onetimeauth_poly1305_verify(bArr, bArr2, i, bArr3);
    }

    public static int n(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_onetimeauth_poly1305_init(bArr, bArr2);
    }

    public static int p(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_onetimeauth_poly1305_update(bArr, bArr2, i);
    }

    public static int o(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_onetimeauth_poly1305_final(bArr, bArr2);
    }

    public static int aN() {
        return SodiumJNI.crypto_pwhash_alg_argon2i13();
    }

    public static int aO() {
        return SodiumJNI.crypto_pwhash_alg_default();
    }

    public static int aP() {
        return SodiumJNI.crypto_pwhash_bytes_min();
    }

    public static int aQ() {
        return SodiumJNI.crypto_pwhash_bytes_max();
    }

    public static int aR() {
        return SodiumJNI.crypto_pwhash_passwd_min();
    }

    public static int aS() {
        return SodiumJNI.crypto_pwhash_passwd_max();
    }

    public static int aT() {
        return SodiumJNI.crypto_pwhash_saltbytes();
    }

    public static int aU() {
        return SodiumJNI.crypto_pwhash_strbytes();
    }

    public static byte[] aV() {
        return SodiumJNI.crypto_pwhash_strprefix();
    }

    public static int aW() {
        return SodiumJNI.crypto_pwhash_opslimit_min();
    }

    public static int aX() {
        return SodiumJNI.crypto_pwhash_opslimit_max();
    }

    public static int aY() {
        return SodiumJNI.crypto_pwhash_memlimit_min();
    }

    public static int aZ() {
        return SodiumJNI.crypto_pwhash_memlimit_max();
    }

    public static int ba() {
        return SodiumJNI.crypto_pwhash_opslimit_interactive();
    }

    public static int bb() {
        return SodiumJNI.crypto_pwhash_memlimit_interactive();
    }

    public static int bc() {
        return SodiumJNI.crypto_pwhash_opslimit_moderate();
    }

    public static int bd() {
        return SodiumJNI.crypto_pwhash_memlimit_moderate();
    }

    public static int be() {
        return SodiumJNI.crypto_pwhash_opslimit_sensitive();
    }

    public static int bf() {
        return SodiumJNI.crypto_pwhash_memlimit_sensitive();
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, int i5) {
        return SodiumJNI.crypto_pwhash(bArr, i, bArr2, i2, bArr3, i3, i4, i5);
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        return SodiumJNI.crypto_pwhash_str(bArr, bArr2, i, i2, i3);
    }

    public static int q(byte[] bArr, byte[] bArr2, int i) {
        return SodiumJNI.crypto_pwhash_str_verify(bArr, bArr2, i);
    }

    public static byte[] bg() {
        return SodiumJNI.crypto_pwhash_primitive();
    }

    public static int bh() {
        return SodiumJNI.crypto_scalarmult_curve25519_bytes();
    }

    public static int bi() {
        return SodiumJNI.crypto_scalarmult_curve25519_scalarbytes();
    }

    public static int g(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_scalarmult_curve25519(bArr, bArr2, bArr3);
    }

    public static int p(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_scalarmult_curve25519_base(bArr, bArr2);
    }

    public static int bj() {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_keybytes();
    }

    public static int bk() {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_noncebytes();
    }

    public static int bl() {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_zerobytes();
    }

    public static int bm() {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_boxzerobytes();
    }

    public static int bn() {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_macbytes();
    }

    public static int j(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int k(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_secretbox_xsalsa20poly1305_open(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int bo() {
        return SodiumJNI.crypto_shorthash_siphash24_bytes();
    }

    public static int bp() {
        return SodiumJNI.crypto_shorthash_siphash24_keybytes();
    }

    public static int p(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_shorthash_siphash24(bArr, bArr2, i, bArr3);
    }

    public static int bq() {
        return SodiumJNI.crypto_sign_ed25519_bytes();
    }

    public static int br() {
        return SodiumJNI.crypto_sign_ed25519_seedbytes();
    }

    public static int bs() {
        return SodiumJNI.crypto_sign_ed25519_publickeybytes();
    }

    public static int bt() {
        return SodiumJNI.crypto_sign_ed25519_secretkeybytes();
    }

    public static int d(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_ed25519(bArr, iArr, bArr2, i, bArr3);
    }

    public static int e(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_ed25519_open(bArr, iArr, bArr2, i, bArr3);
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_stream_xsalsa20(bArr, i, bArr2, bArr3);
    }

    public static int f(byte[] bArr, int[] iArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_ed25519_detached(bArr, iArr, bArr2, i, bArr3);
    }

    public static int q(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return SodiumJNI.crypto_sign_ed25519_verify_detached(bArr, bArr2, i, bArr3);
    }

    public static int q(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_ed25519_keypair(bArr, bArr2);
    }

    public static int h(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_sign_ed25519_seed_keypair(bArr, bArr2, bArr3);
    }

    public static int r(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_ed25519_pk_to_curve25519(bArr, bArr2);
    }

    public static int s(byte[] bArr, byte[] bArr2) {
        return SodiumJNI.crypto_sign_ed25519_sk_to_curve25519(bArr, bArr2);
    }

    public static int bu() {
        return SodiumJNI.crypto_stream_chacha20_keybytes();
    }

    public static int bv() {
        return SodiumJNI.crypto_stream_chacha20_noncebytes();
    }

    public static int b(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_stream_chacha20(bArr, i, bArr2, bArr3);
    }

    public static int l(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_stream_chacha20_xor(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4) {
        return SodiumJNI.crypto_stream_chacha20_xor_ic(bArr, bArr2, i, bArr3, i2, bArr4);
    }

    public static int bw() {
        return SodiumJNI.crypto_stream_chacha20_ietf_noncebytes();
    }

    public static int c(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_stream_chacha20_ietf(bArr, i, bArr2, bArr3);
    }

    public static int m(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_stream_chacha20_ietf_xor(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4) {
        return SodiumJNI.crypto_stream_chacha20_ietf_xor_ic(bArr, bArr2, i, bArr3, i2, bArr4);
    }

    public static int bx() {
        return SodiumJNI.crypto_stream_salsa20_keybytes();
    }

    public static int by() {
        return SodiumJNI.crypto_stream_salsa20_noncebytes();
    }

    public static int d(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return SodiumJNI.crypto_stream_salsa20(bArr, i, bArr2, bArr3);
    }

    public static int n(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_stream_salsa20_xor(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int c(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4) {
        return SodiumJNI.crypto_stream_salsa20_xor_ic(bArr, bArr2, i, bArr3, i2, bArr4);
    }

    public static int bz() {
        return SodiumJNI.crypto_stream_xsalsa20_keybytes();
    }

    public static int bA() {
        return SodiumJNI.crypto_stream_xsalsa20_noncebytes();
    }

    public static int o(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return SodiumJNI.crypto_stream_xsalsa20_xor(bArr, bArr2, i, bArr3, bArr4);
    }

    public static int d(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4) {
        return SodiumJNI.crypto_stream_xsalsa20_xor_ic(bArr, bArr2, i, bArr3, i2, bArr4);
    }
}
