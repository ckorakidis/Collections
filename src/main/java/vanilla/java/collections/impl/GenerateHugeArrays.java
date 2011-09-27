/*
 * Copyright (c) 2011 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vanilla.java.collections.impl;

import org.objectweb.asm.*;
import vanilla.java.collections.api.impl.FieldModel;
import vanilla.java.collections.api.impl.TypeModel;
import vanilla.java.collections.model.Enum8FieldModel;
import vanilla.java.collections.model.Enumerated16FieldModel;

import static org.objectweb.asm.Opcodes.*;

public enum GenerateHugeArrays {
    ;

    public static byte[] dumpArrayList(TypeModel tm) {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        Class interfaceClass = tm.baseType();
        String name = interfaceClass.getName().replace('.', '/');

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, name + "ArrayList", "Lvanilla/java/collections/impl/AbstractHugeArrayList<L" + name + ";>;", "vanilla/java/collections/impl/AbstractHugeArrayList", null);

        cw.visitSource(tm.baseType().getSimpleName() + "ArrayList.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(ILjava/lang/Class;Lvanilla/java/collections/api/impl/ByteBufferAllocator;)V", "(ILjava/lang/Class<L" + name + ";>;Lvanilla/java/collections/api/impl/ByteBufferAllocator;)V", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(28, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKESPECIAL, "vanilla/java/collections/impl/AbstractHugeArrayList", "<init>", "(ILjava/lang/Class;Lvanilla/java/collections/api/impl/ByteBufferAllocator;)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(29, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l2, 0);
            mv.visitLocalVariable("partitionSize", "I", null, l0, l2, 1);
            mv.visitLocalVariable("elementType", "Ljava/lang/Class;", "Ljava/lang/Class<L" + name + ";>;", l0, l2, 2);
            mv.visitLocalVariable("allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;", null, l0, l2, 3);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED, "createPartition", "(I)L" + name + "Partition;", null, new String[]{"java/io/IOException"});
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(32, l0);
            mv.visitTypeInsn(NEW, name + "Partition");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "ArrayList", "allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;");
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, name + "Partition", "<init>", "(L" + name + "ArrayList;Lvanilla/java/collections/api/impl/ByteBufferAllocator;I)V");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitLocalVariable("partitionNumber", "I", null, l0, l1, 1);
            mv.visitMaxs(5, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED, "createPointer", "()L" + name + ";", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(37, l0);
            mv.visitTypeInsn(NEW, name + "Pointer");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name + "Pointer", "<init>", "(L" + name + "ArrayList;)V");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitMaxs(3, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED, "createImpl", "()L" + name + ";", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(42, l0);
            mv.visitTypeInsn(NEW, name + "Impl");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, name + "Impl", "<init>", "()V");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED, "createIterator", "()Lvanilla/java/collections/api/HugeListIterator;", "()Lvanilla/java/collections/api/HugeListIterator<L" + name + ";>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(47, l0);
            mv.visitTypeInsn(NEW, "vanilla/java/collections/impl/VanillaHugeListIterator");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "createPointer", "()L" + name + ";");
            mv.visitMethodInsn(INVOKESPECIAL, "vanilla/java/collections/impl/VanillaHugeListIterator", "<init>", "(Lvanilla/java/collections/impl/AbstractHugeCollection;Ljava/lang/Object;)V");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "partitionFor", "(J)L" + name + "Partition;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(51, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, "vanilla/java/collections/impl/AbstractHugeArrayList", "partitionFor", "(J)Lvanilla/java/collections/api/impl/HugePartition;");
            mv.visitTypeInsn(CHECKCAST, name + "Partition");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitLocalVariable("index", "J", null, l0, l1, 1);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "pointerPoolAdd", "(L" + name + "Pointer;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(55, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "ArrayList", "pointerPool", "Ljava/util/Deque;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Deque", "add", "(Ljava/lang/Object;)Z");
            mv.visitInsn(POP);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(56, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l2, 0);
            mv.visitLocalVariable("htPointer", "L" + name + "Pointer;", null, l0, l2, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "implPoolAdd", "(L" + name + "Impl;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(59, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "ArrayList", "implPool", "Ljava/util/Deque;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Deque", "add", "(Ljava/lang/Object;)Z");
            mv.visitInsn(POP);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(60, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l2, 0);
            mv.visitLocalVariable("htImpl", "L" + name + "Impl;", null, l0, l2, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "recycle", "(Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(64, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(INSTANCEOF, name + "Pointer");
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(65, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "Pointer");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "pointerPoolAdd", "(L" + name + "Pointer;)V");
            Label l3 = new Label();
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l1);
            mv.visitLineNumber(66, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(INSTANCEOF, name + "Impl");
            Label l4 = new Label();
            mv.visitJumpInsn(IFEQ, l4);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(67, l5);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "Impl");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "implPoolAdd", "(L" + name + "Impl;)V");
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l4);
            mv.visitLineNumber(69, l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, "vanilla/java/collections/impl/AbstractHugeArrayList", "recycle", "(Ljava/lang/Object;)V");
            mv.visitLabel(l3);
            mv.visitLineNumber(70, l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l6, 0);
            mv.visitLocalVariable("recycleable", "Ljava/lang/Object;", null, l0, l6, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED + ACC_BRIDGE + ACC_SYNTHETIC, "createPartition", "(I)Lvanilla/java/collections/api/impl/HugePartition;", null, new String[]{"java/io/IOException"});
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(26, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "createPartition", "(I)L" + name + "Partition;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitLocalVariable("x0", "I", null, l0, l1, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "partitionFor", "(J)Lvanilla/java/collections/api/impl/HugePartition;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(26, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionFor", "(J)L" + name + "Partition;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitLocalVariable("x0", "J", null, l0, l1, 1);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED + ACC_BRIDGE + ACC_SYNTHETIC, "createImpl", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(26, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "createImpl", "()L" + name + ";");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED + ACC_BRIDGE + ACC_SYNTHETIC, "createPointer", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(26, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "createPointer", "()L" + name + ";");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "ArrayList;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        cw.visitEnd();
        final byte[] bytes = cw.toByteArray();
//        ClassReader cr = new ClassReader(bytes);
//        cr.accept(new ASMifierClassVisitor(new PrintWriter(System.out)), 0);
        return bytes;
    }

    public static byte[] dumpPartition(TypeModel tm) {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        Class interfaceClass = tm.baseType();
        String name = interfaceClass.getName().replace('.', '/');

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, name + "Partition", null, "java/lang/Object", new String[]{"vanilla/java/collections/api/impl/HugePartition"});

        cw.visitSource(tm.baseType().getSimpleName() + "Partition.java", null);

        for (FieldModel fm : tm.fields()) {
            if (fm instanceof Enum8FieldModel) {
                fv = cw.visitField(ACC_FINAL, fm.fieldName() + "FieldModel", "Lvanilla/java/collections/model/Enum8FieldModel;", "Lvanilla/java/collections/model/Enum8FieldModel<Ljava/lang/annotation/ElementType;>;", null);
                fv.visitEnd();
            } else if (fm instanceof Enumerated16FieldModel) {
                fv = cw.visitField(ACC_FINAL, fm.fieldName() + "FieldModel", "Lvanilla/java/collections/model/Enumerated16FieldModel;", "Lvanilla/java/collections/model/Enumerated16FieldModel<Ljava/lang/String;>;", null);
                fv.visitEnd();
            }
        }

        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "list", "L" + name + "ArrayList;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "partitionNumber", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "reserved", "Lvanilla/java/collections/api/impl/Cleaner;", null, null);
            fv.visitEnd();
        }
        for (FieldModel fm : tm.fields()) {
            fv = cw.visitField(0, fm.fieldName() + "Buffer", fm.bcLStoreType(), null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(L" + name + "ArrayList;Lvanilla/java/collections/api/impl/ByteBufferAllocator;I)V", null, new String[]{"java/io/IOException"});
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(39, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");

            for (FieldModel fm : tm.fields()) {
                if (fm instanceof Enum8FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitTypeInsn(NEW, fm.bcModelType());
                    mv.visitInsn(DUP);
                    mv.visitLdcInsn(fm.fieldName());
                    mv.visitLdcInsn(Type.getType(fm.bcLFieldType()));
                    mv.visitMethodInsn(INVOKESTATIC, fm.bcFieldType(), "values", "()[" + fm.bcLFieldType());
                    mv.visitMethodInsn(INVOKESPECIAL, fm.bcModelType(), "<init>", "(Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Enum;)V");
                    mv.visitFieldInsn(PUTFIELD, name + "ArrayList", fm.fieldName() + "FieldModel", fm.bcLModelType());
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "ArrayList", fm.fieldName() + "FieldModel", fm.bcLModelType());
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/HugeArrayBuilder", "baseDirectory", "()Ljava/lang/String;");
                    mv.visitMethodInsn(INVOKEVIRTUAL, fm.bcModelType(), "baseDirectory", "(Ljava/lang/String;)V");

                } else if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitTypeInsn(NEW, fm.bcModelType());
                    mv.visitInsn(DUP);
                    mv.visitLdcInsn(fm.fieldName());
                    mv.visitLdcInsn(Type.getType(fm.bcLFieldType()));
                    mv.visitMethodInsn(INVOKESPECIAL, fm.bcModelType(), "<init>", "(Ljava/lang/String;Ljava/lang/Class;)V");
                    mv.visitFieldInsn(PUTFIELD, name + "ArrayList", fm.fieldName() + "FieldModel", fm.bcLModelType());
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "ArrayList", fm.fieldName() + "FieldModel", fm.bcLModelType());
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/HugeArrayBuilder", "baseDirectory", "()Ljava/lang/String;");
                    mv.visitMethodInsn(INVOKEVIRTUAL, fm.bcModelType(), "baseDirectory", "(Ljava/lang/String;)V");
                }
            }

            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(40, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Partition", "list", "L" + name + "ArrayList;");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(41, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, name + "Partition", "allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;");
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(42, l4);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitFieldInsn(PUTFIELD, name + "Partition", "partitionNumber", "I");
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(43, l5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionSize", "()I");
            mv.visitVarInsn(ISTORE, 4);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(44, l6);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitIntInsn(BIPUSH, 6);
            mv.visitLdcInsn("part");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/ByteBufferAllocator", "reserve", "(IILjava/lang/String;I)Lvanilla/java/collections/api/impl/Cleaner;");
            mv.visitFieldInsn(PUTFIELD, name + "Partition", "reserved", "Lvanilla/java/collections/api/impl/Cleaner;");
            for (FieldModel fm : tm.fields()) {
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/ByteBufferAllocator", fm.acquireStoreType(), "()" + fm.bcLStoreType());
                mv.visitFieldInsn(PUTFIELD, name + "Partition", fm.fieldName() + "Buffer", fm.bcLStoreType());
            }

            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(47, l9);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/ByteBufferAllocator", "endOfReserve", "()V");

            for (FieldModel fm : tm.fields()) {
                if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.bcModelType(), fm.bcLModelType());
                    mv.visitVarInsn(ALOAD, 2);
                    mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/ByteBufferAllocator", "baseDirectory", "()Ljava/io/File;");
                    mv.visitVarInsn(ILOAD, 3);
                    mv.visitMethodInsn(INVOKEVIRTUAL, fm.bcModelType(), "load", "(Ljava/io/File;I)V");
                }
            }
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(49, l10);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLineNumber(50, l11);
            mv.visitInsn(RETURN);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l12, 0);
            mv.visitLocalVariable("list", "L" + name + "ArrayList;", null, l0, l12, 1);
            mv.visitLocalVariable("allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;", null, l0, l12, 2);
            mv.visitLocalVariable("partitionNumber", "I", null, l0, l12, 3);
            mv.visitLocalVariable("partitionSize", "I", null, l6, l12, 4);
            mv.visitMaxs(6, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "lock", "()Ljava/util/concurrent/locks/ReadWriteLock;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(54, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "list", "L" + name + "ArrayList;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "lock", "()Ljava/util/concurrent/locks/ReadWriteLock;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "clear", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            for (FieldModel fm : tm.fields()) {
                if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.bcModelType(), fm.bcLModelType());
                    mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/impl/Enumerated16FieldModel", "clear", "()V");
                }
            }
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(60, l1);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            for (FieldModel fm : tm.fields()) {
                if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.fieldName() + "Buffer", "Ljava/nio/CharBuffer;");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/nio/CharBuffer", "capacity", "()I");
                    Label l3 = new Label();
                    mv.visitJumpInsn(IF_ICMPGE, l3);
                    Label l4 = new Label();
                    mv.visitLabel(l4);
                    mv.visitLineNumber(61, l4);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.fieldName() + "Buffer", "Ljava/nio/CharBuffer;");
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitInsn(ICONST_0);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/nio/CharBuffer", "put", "(IC)Ljava/nio/CharBuffer;");
                    mv.visitInsn(POP);
                    Label l5 = new Label();
                    mv.visitLabel(l5);
                    mv.visitLineNumber(60, l5);
                    mv.visitIincInsn(1, 1);
                    mv.visitJumpInsn(GOTO, l2);
                    mv.visitLabel(l3);
                    mv.visitLineNumber(62, l3);
                }
            }
            mv.visitInsn(RETURN);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLocalVariable("i", "I", null, l2, l6, 1);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l6, 0);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "destroy", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(66, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "reserved", "Lvanilla/java/collections/api/impl/Cleaner;");
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/Cleaner", "clean", "()V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(67, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l2, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        for (FieldModel fm : tm.fields()) {
            {
                mv = cw.visitMethod(ACC_PUBLIC, "set" + fm.titleFieldName(), "(I" + fm.bcLFieldType() + ")V", null, null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitLineNumber(70, l0);
                if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", "textModel", "Lvanilla/java/collections/impl/Enumerated16FieldModel;");
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", "textBuffer", "Ljava/nio/CharBuffer;");
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitVarInsn(ALOAD, 2);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/impl/Enumerated16FieldModel", "set", "(Ljava/nio/CharBuffer;ILjava/lang/Object;)V");
                } else {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.fieldName() + "Buffer", fm.bcLStoreType());
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitVarInsn(ILOAD, 2);
                    mv.visitMethodInsn(INVOKEVIRTUAL, fm.bcStoreType(), "put", "(I" + fm.bcLFieldType() + ")" + fm.bcLStoredType());
                    mv.visitInsn(POP);
                }


                Label l1 = new Label();
                mv.visitLabel(l1);
                mv.visitLineNumber(71, l1);
                mv.visitInsn(RETURN);
                Label l2 = new Label();
                mv.visitLabel(l2);
                mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l2, 0);
                mv.visitLocalVariable("offset", "I", null, l0, l2, 1);
                mv.visitLocalVariable("i", "I", null, l0, l2, 2);
                mv.visitMaxs(4, 3);
                mv.visitEnd();
            }
            {
                mv = cw.visitMethod(ACC_PUBLIC, "get" + fm.titleFieldName(), "(I)" + fm.bcLFieldType(), null, null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitLineNumber(74, l0);
                if (fm instanceof Enumerated16FieldModel) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", "textModel", "Lvanilla/java/collections/impl/Enumerated16FieldModel;");
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", "textBuffer", "Ljava/nio/CharBuffer;");
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/impl/Enumerated16FieldModel", "get", "(Ljava/nio/CharBuffer;I)Ljava/lang/Object;");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/String");
                    mv.visitInsn(ARETURN);
                } else {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, name + "Partition", fm.fieldName() + "Buffer", fm.bcLStoreType());
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKEVIRTUAL, fm.bcStoreType(), "get", "(I)" + fm.bcLStoredType());
                    mv.visitInsn(IRETURN);
                }
                Label l1 = new Label();
                mv.visitLabel(l1);
                mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l1, 0);
                mv.visitLocalVariable("offset", "I", null, l0, l1, 1);
                mv.visitMaxs(3, 2);
                mv.visitEnd();
            }
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "compact", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(87, l0);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l1, 0);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "flush", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(90, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "allocator", "Lvanilla/java/collections/api/impl/ByteBufferAllocator;");
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/ByteBufferAllocator", "baseDirectory", "()Ljava/io/File;");
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(91, l1);
            mv.visitVarInsn(ALOAD, 1);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNONNULL, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l2);
            mv.visitLineNumber(92, l2);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/io/File"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "textModel", "Lvanilla/java/collections/impl/Enumerated16FieldModel;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "partitionNumber", "I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "vanilla/java/collections/impl/Enumerated16FieldModel", "save", "(Ljava/io/File;I)V");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(93, l3);
            mv.visitInsn(RETURN);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l4, 0);
            mv.visitLocalVariable("dir", "Ljava/io/File;", null, l1, l4, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "close", "()V", null, new String[]{"java/io/IOException"});
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(97, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Partition", "flush", "()V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(98, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Partition", "reserved", "Lvanilla/java/collections/api/impl/Cleaner;");
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/Cleaner", "clean", "()V");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(99, l2);
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "L" + name + "Partition;", null, l0, l3, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    public static byte[] dumpPointer(TypeModel tm) {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        Class interfaceClass = tm.baseType();
        String name = interfaceClass.getName().replace('.', '/');

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name + "Pointer", "Ljava/lang/Object;L" + name + ";Lvanilla/java/collections/api/impl/HugeElement;Lvanilla/java/collections/api/impl/Copyable<L" + name + ";>;", "java/lang/Object", new String[]{name + "", "vanilla/java/collections/api/impl/HugeElement", "vanilla/java/collections/api/impl/Copyable"});

        cw.visitSource("HTPointer.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE, "index", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "list", "L" + name + "ArrayList;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "partition", "L" + name + "Partition;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "offset", "I", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(L" + name + "ArrayList;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(29, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(24, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(new Long(-9223372036854775808L));
            mv.visitFieldInsn(PUTFIELD, name + "Pointer", "index", "J");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(30, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(31, l3);
            mv.visitInsn(RETURN);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l4, 0);
            mv.visitLocalVariable("list", "L" + name + "ArrayList;", null, l0, l4, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setInt", "(I)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(35, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "partition", "L" + name + "Partition;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "offset", "I");
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Partition", "setInt", "(II)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(36, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l2, 0);
            mv.visitLocalVariable("i", "I", null, l0, l2, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getInt", "()I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(40, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "partition", "L" + name + "Partition;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "offset", "I");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Partition", "getInt", "(I)I");
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setText", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(45, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "partition", "L" + name + "Partition;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "offset", "I");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Partition", "setText", "(ILjava/lang/String;)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(46, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l2, 0);
            mv.visitLocalVariable("id", "Ljava/lang/String;", null, l0, l2, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getText", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(50, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "partition", "L" + name + "Partition;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "offset", "I");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Partition", "getText", "(I)Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longHashCode", "()J", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(55, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getInt", "()I");
            mv.visitMethodInsn(INVOKESTATIC, "vanilla/java/collections/util/HugeCollections", "hashCode", "(I)I");
            mv.visitInsn(I2L);
            mv.visitLdcInsn(new Long(31L));
            mv.visitInsn(LMUL);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKESTATIC, "vanilla/java/collections/util/HugeCollections", "hashCode", "(Ljava/lang/Object;)J");
            mv.visitInsn(LADD);
            mv.visitInsn(LRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "index", "()J", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(60, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "index", "J");
            mv.visitInsn(LRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "index", "(J)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(65, l0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionSize", "()I");
            mv.visitInsn(I2L);
            mv.visitInsn(LDIV);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "index", "J");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionSize", "()I");
            mv.visitInsn(I2L);
            mv.visitInsn(LDIV);
            mv.visitInsn(LCMP);
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(66, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionFor", "(J)L" + name + "Partition;");
            mv.visitFieldInsn(PUTFIELD, name + "Pointer", "partition", "L" + name + "Partition;");
            mv.visitLabel(l1);
            mv.visitLineNumber(68, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Pointer", "index", "J");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(69, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "partitionSize", "()I");
            mv.visitInsn(I2L);
            mv.visitInsn(LREM);
            mv.visitInsn(L2I);
            mv.visitFieldInsn(PUTFIELD, name + "Pointer", "offset", "I");
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(70, l4);
            mv.visitInsn(RETURN);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l5, 0);
            mv.visitLocalVariable("index", "J", null, l0, l5, 1);
            mv.visitMaxs(6, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "recycle", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(74, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "pointerPoolAdd", "(L" + name + "Pointer;)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(75, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l2, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "copyFrom", "(L" + name + ";)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(79, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getInt", "()I");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "setInt", "(I)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(80, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "setText", "(Ljava/lang/String;)V");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(81, l2);
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l3, 0);
            mv.visitLocalVariable("ht", "L" + name + ";", null, l0, l3, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "copyOf", "()L" + name + ";", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(85, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Pointer", "list", "L" + name + "ArrayList;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "ArrayList", "acquireImpl", "()Ljava/lang/Object;");
            mv.visitTypeInsn(CHECKCAST, name + "");
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(86, l1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, "vanilla/java/collections/api/impl/Copyable");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEINTERFACE, "vanilla/java/collections/api/impl/Copyable", "copyFrom", "(Ljava/lang/Object;)V");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(87, l2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(ARETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l3, 0);
            mv.visitLocalVariable("ht", "L" + name + ";", null, l1, l3, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "equals", "(Ljava/lang/Object;)Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(93, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ACMPNE, l1);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(94, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(INSTANCEOF, name + "");
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitLineNumber(96, l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "");
            mv.visitVarInsn(ASTORE, 2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(98, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getInt", "()I");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getInt", "()I");
            Label l4 = new Label();
            mv.visitJumpInsn(IF_ICMPEQ, l4);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l4);
            mv.visitLineNumber(99, l4);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{name + ""}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getText", "()Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");
            Label l5 = new Label();
            mv.visitJumpInsn(IFNE, l5);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l5);
            mv.visitLineNumber(101, l5);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l6, 0);
            mv.visitLocalVariable("o", "Ljava/lang/Object;", null, l0, l6, 1);
            mv.visitLocalVariable("ht", "L" + name + ";", null, l3, l6, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "hashCode", "()I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(106, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getInt", "()I");
            mv.visitIntInsn(BIPUSH, 31);
            mv.visitInsn(IMUL);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "hashCode", "()I");
            mv.visitInsn(IADD);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(112, l0);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitLdcInsn("HT{int=");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getInt", "()I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn(", text='");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitIntInsn(BIPUSH, 39);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;");
            mv.visitIntInsn(BIPUSH, 125);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "copyOf", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(23, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "copyOf", "()L" + name + ";");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "copyFrom", "(Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(23, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Pointer", "copyFrom", "(L" + name + ";)V");
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Pointer;", null, l0, l1, 0);
            mv.visitLocalVariable("x0", "Ljava/lang/Object;", null, l0, l1, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    public static byte[] dumpImpl(TypeModel tm) {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        Class interfaceClass = tm.baseType();
        String name = interfaceClass.getName().replace('.', '/');
        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name + "Impl", "Ljava/lang/Object;L" + name + ";Lvanilla/java/collections/api/impl/Copyable<L" + name + ";>;", "java/lang/Object", new String[]{name + "", "vanilla/java/collections/api/impl/Copyable"});

        cw.visitSource("HTImpl.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE, "m_int", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "m_text", "Ljava/lang/String;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(25, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(26, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l2, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(ILjava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(28, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(29, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Impl", "m_int", "I");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(30, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, name + "Impl", "m_text", "Ljava/lang/String;");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(31, l3);
            mv.visitInsn(RETURN);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l4, 0);
            mv.visitLocalVariable("m_int", "I", null, l0, l4, 1);
            mv.visitLocalVariable("m_text", "Ljava/lang/String;", null, l0, l4, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getInt", "()I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(35, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Impl", "m_int", "I");
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setInt", "(I)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(40, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Impl", "m_int", "I");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(41, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l2, 0);
            mv.visitLocalVariable("i", "I", null, l0, l2, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setText", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(45, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, name + "Impl", "m_text", "Ljava/lang/String;");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(46, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l2, 0);
            mv.visitLocalVariable("id", "Ljava/lang/String;", null, l0, l2, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getText", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(50, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Impl", "m_text", "Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "copyFrom", "(L" + name + ";)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(55, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getInt", "()I");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "setInt", "(I)V");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(56, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "setText", "(Ljava/lang/String;)V");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(57, l2);
            mv.visitInsn(RETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l3, 0);
            mv.visitLocalVariable("ht", "L" + name + ";", null, l0, l3, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "copyOf", "()L" + name + ";", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(61, l0);
            mv.visitTypeInsn(NEW, name + "Impl");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Impl", "m_int", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, name + "Impl", "m_text", "Ljava/lang/String;");
            mv.visitMethodInsn(INVOKESPECIAL, name + "Impl", "<init>", "(ILjava/lang/String;)V");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "equals", "(Ljava/lang/Object;)Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(66, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ACMPNE, l1);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(67, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(INSTANCEOF, name + "");
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitLineNumber(69, l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "");
            mv.visitVarInsn(ASTORE, 2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(71, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getInt", "()I");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getInt", "()I");
            Label l4 = new Label();
            mv.visitJumpInsn(IF_ICMPEQ, l4);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l4);
            mv.visitLineNumber(72, l4);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{name + ""}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getText", "()Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, name + "", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");
            Label l5 = new Label();
            mv.visitJumpInsn(IFNE, l5);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l5);
            mv.visitLineNumber(74, l5);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l6, 0);
            mv.visitLocalVariable("o", "Ljava/lang/Object;", null, l0, l6, 1);
            mv.visitLocalVariable("ht", "L" + name + ";", null, l3, l6, 2);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "hashCode", "()I", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(79, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getInt", "()I");
            mv.visitIntInsn(BIPUSH, 31);
            mv.visitInsn(IMUL);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "hashCode", "()I");
            mv.visitInsn(IADD);
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(84, l0);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
            mv.visitLdcInsn("HT{int=");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getInt", "()I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
            mv.visitLdcInsn(", text='");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "getText", "()Ljava/lang/String;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            mv.visitIntInsn(BIPUSH, 39);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;");
            mv.visitIntInsn(BIPUSH, 125);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(C)Ljava/lang/StringBuilder;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "copyOf", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(21, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "copyOf", "()L" + name + ";");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "copyFrom", "(Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(21, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(CHECKCAST, name + "");
            mv.visitMethodInsn(INVOKEVIRTUAL, name + "Impl", "copyFrom", "(L" + name + ";)V");
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "L" + name + "Impl;", null, l0, l1, 0);
            mv.visitLocalVariable("x0", "Ljava/lang/Object;", null, l0, l1, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}


