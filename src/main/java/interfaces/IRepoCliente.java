/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author mylle
 */
public interface IRepoCliente<T, t,x> {
  T save(T obj); 
  T getCpf(String id);
  x getid(String id);
  void update(T obj);
  List<T> getAll();
}

