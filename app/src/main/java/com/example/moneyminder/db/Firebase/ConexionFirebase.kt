package com.example.moneyminder.db.Firebase

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb
import com.example.moneyminder.model_de_datos.Gastos
import com.example.moneyminder.model_de_datos.Usuario
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.UUID

final class ConexionFirebase (context: Context) {
    //Obtenemos una instancia de Firebase Firestore.
    val db = Firebase.firestore
    var crearDb = CrearDb(context)
    val dbInterna = crearDb.writableDatabase

    private val dataBase = FirebaseFirestore.getInstance()
    fun insertarGasto(gasto: Gastos) {

        // Obtenemos una referencia al documento en la colección "gastos" usando el correo electrónico del usuario
        val docRef = db.collection("gastos").document(LeerDatosDb().getCorreoElectronico(dbInterna).toString()+"_gastos");

        // Actualizamos el campo del documento con el nuevo gasto con un id automático.
        docRef.update(UUID.randomUUID().toString(), FieldValue.arrayUnion(gasto))
            .addOnSuccessListener {
                // En caso de éxito, imprimimos un mensaje de registro indicando que el documento se actualizó correctamente
                Log.d(TAG, "El documento se actualizó correctamente")
            }
            .addOnFailureListener { e ->
                // En caso de error al actualizar el documento

                // Verificar si el error es debido a que el documento no existe
                if (e is FirebaseFirestoreException && e.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                    // Si el documento no existe, crear un nuevo documento con el nuevo gasto

                    // Crear los datos iniciales con el nuevo gasto con un id automático.
                    val initialData = hashMapOf(
                        UUID.randomUUID().toString() to arrayListOf(gasto)
                    )

                    // Escribir los datos iniciales en el documento
                    docRef.set(initialData)
                        .addOnSuccessListener {
                            // En caso de éxito, imprimir un mensaje de registro indicando que el documento se escribió correctamente
                            Log.d(TAG, "Documento escrito correctamente")
                        }
                        .addOnFailureListener { e ->
                            // En caso de error al escribir el documento, imprimir un mensaje de advertencia con el error
                            Log.w(TAG, "Error al escribir el documento", e)
                        }
                } else {
                    // Si el error no se debe a que el documento no existe, imprimir un mensaje de advertencia con el error
                    Log.w(TAG, "Error updating document", e)
                }
            }
    }

    fun ingresarUsuario(usuario: Usuario){
        // Obtenemos una referencia al documento en la colección "gastos" usando el correo electrónico del usuario
        val docRef = db.collection("usuarios").document(LeerDatosDb().getCorreoElectronico(dbInterna).toString()+"_usuario");

        val initialData = hashMapOf(
            UUID.randomUUID().toString() to arrayListOf(usuario)
        )

        // Escribir los datos iniciales en el documento
        docRef.set(initialData)
            .addOnSuccessListener {
                // En caso de éxito, imprimir un mensaje de registro indicando que el documento se escribió correctamente
                Log.d(TAG, "Documento escrito correctamente")
            }
            .addOnFailureListener { e ->
                // En caso de error al escribir el documento, imprimir un mensaje de advertencia con el error
                Log.w(TAG, "Error al escribir el documento", e)
            }

    }


}