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
public interface IRepoCliente<T> {
  T save(T obj);
  void update(T obj);
  List<T> getAll();
}

