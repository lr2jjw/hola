package com.bvm.mci.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bmv.mci.model.Destinatario;
import com.bvm.mci.dto.DestinatarioSimple;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;

public class AdmonAlertasServiceImplTest {

    @InjectMocks
    private AdmonAlertasServiceImpl admonAlertasService;

    @Mock
    private SessionFactory sessionFactoryOds;

    @Mock
    private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

    @Mock
    private Session session;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Cambia openMocks por initMocks
        when(sessionFactoryOds.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testGetListaDestinatarios() {
        DestinatarioSimple destinatarioSimple = new DestinatarioSimple(1L, "tipo1", "Contacto1", "destino1");

        // Mock de la consulta
        Query mockQuery =  mock(Query.class);
      //  when(session.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameterList(eq("lista"), anyList())).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(destinatarioSimple));

        // Ejecutar el método
        List<DestinatarioSimple> result = admonAlertasService.getListaDestinatarios();

        // Verificar resultados
        assertEquals(1, result.size());
        assertEquals("Contacto1", result.get(0).getNombreContacto());
    }


    @Test
    void testSaveDestinatario() {
        // Ejecutar el método
        Long result = admonAlertasService.saveDestinatario("tipo1", "Contacto1", "destino1");

        // Verificar resultados
        assertEquals(Long.valueOf(1), result);
        verify(session).persist(any(Destinatario.class));
    }

    @Test
    void testDeleteDestinatario() {
        // Configurar el mock
        Long id = 1L;
        Query<Destinatario> mockQuery = mock(Query.class);
       // when(session.createQuery(anyString(), eq(Destinatario.class))).thenReturn(mockQuery);
        when(mockQuery.setParameterList(eq("lista"), anyList())).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(new Destinatario()));

        // Ejecutar el método
        Long result = admonAlertasService.deleteDestinatario(Arrays.asList(id));

        // Verificar resultados
        assertEquals(Long.valueOf(1), result);
        verify(session).delete(any(Destinatario.class));
    }

    @Test
    void testUpdateDestinatario() {
        // Configurar el mock
        Destinatario destinatario = new Destinatario();
        destinatario.setId(206L);
        destinatario.setTipo("email1");
        destinatario.setNombreContacto("pruebaqacg 123");
        destinatario.setDestino("pruebaqacg@qagc.com.mx");

        Query<Destinatario> mockQuery = mock(Query.class);
     //   when(session.createQuery(anyString(), eq(Destinatario.class))).thenReturn(mockQuery);
        when(mockQuery.setParameterList(eq("lista"), anyList())).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList(destinatario));

        // Crear una lista de DestinatarioSimple para actualizar
        List<DestinatarioSimple> listaActualizar = Arrays.asList(new DestinatarioSimple(1L, "tipo2", "Contacto2", "destino2"));

        // Ejecutar el método
        Long result = admonAlertasService.updateDestinatario(listaActualizar);

        // Verificar resultados
        assertEquals(Long.valueOf(1), result);
        verify(session).update(any(Destinatario.class));
    }
}
