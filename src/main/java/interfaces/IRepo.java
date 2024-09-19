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
public interface IRepo<T, t> {
  T save(T obj); 
  T get(t id);
  void update(T obj);
  List<T> getAll();
  void delete(t id);
}

