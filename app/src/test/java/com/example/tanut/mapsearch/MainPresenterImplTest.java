package com.example.tanut.mapsearch;

import com.example.tanut.mapsearch.data.db.MyItemReader;
import com.example.tanut.mapsearch.data.db.backend.AppDatabase;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.ui.main.MainMvpView;
import com.example.tanut.mapsearch.ui.main.MainPresenterImpl;
import com.example.tanut.mapsearch.ui.map.MapMvpView;
import com.example.tanut.mapsearch.ui.map.MapPresenterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;

/**
 * Created by tanut on 10/19/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MainMvpView view ;

    @Mock
    MyItemReader mReader;

    @Mock
    AppDatabase database;

    private MainPresenterImpl presenter;
    private static final List<MyItem> MANY_ITEM = Arrays.asList(new MyItem(0,0), new MyItem(0,0), new MyItem(0,0));

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view,mReader,database);
    }

    @Test
    public void shouldPassItems() {
        Mockito.when(mReader.read(null)).thenReturn(MANY_ITEM);
        presenter.getGeoPlaceData("TEST",null);
        verify(view).manageLocalData(MANY_ITEM);
    }

    @Test
    public void shouldHandleNoItemFound() {
        Mockito.when(mReader.read(null)).thenReturn(EMPTY_LIST);
        presenter.getGeoPlaceData("TEST",null);
        verify(view).showMessage("NO ITEM");
    }
/*
    @Test
    public void shouldHandleerror() {
        Mockito.when(mReader.read(null)).thenThrow(new RuntimeException());
        presenter.getGeoPlaceData("TEST",null);
        verify(view).showMessage("ON ERROR");
    }*/
    @After
    public void cleanUp() {
    }
}
