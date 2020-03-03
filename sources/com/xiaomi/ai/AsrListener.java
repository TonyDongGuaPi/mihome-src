package com.xiaomi.ai;

public interface AsrListener {

    public static class SimpleAsrListener implements AsrListener {
        public void a() {
        }

        public void a(float f) {
        }

        public void a(SpeechError speechError) {
        }

        public void a(SpeechResult speechResult) {
        }

        public void a(String str, String str2) {
        }

        public void a(byte[] bArr) {
        }

        public void b() {
        }

        public void b(SpeechResult speechResult) {
        }

        public void c() {
        }

        public void d() {
        }
    }

    void a();

    void a(float f);

    void a(SpeechError speechError);

    void a(SpeechResult speechResult);

    void a(String str, String str2);

    void a(byte[] bArr);

    void b();

    void b(SpeechResult speechResult);

    void c();

    void d();
}
