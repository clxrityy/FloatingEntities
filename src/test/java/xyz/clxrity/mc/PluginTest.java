package xyz.clxrity.mc;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple Plugin.
 */
public class PluginTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     *  Test EntityManager
     *  ----------------
     *  EntityManager should be empty when created
     *  EntityManager should be empty when removing an entity that does not exist
     *  EntityManager should be empty when removing all entities
     */
    @Test
    public void testEntityManager()
    {
        EntityManager em = new EntityManager();
        assertTrue(em.getEntities().isEmpty());
    }

    @Test
    public void testEntityManagerRemoveEntity()
    {
        EntityManager em = new EntityManager();
        em.removeEntity(null);
        assertTrue(em.getEntities().isEmpty());
    }

    @Test
    public void testEntityManagerRemoveAllEntities()
    {
        EntityManager em = new EntityManager();
        em.removeAllEntities();
        assertTrue(em.getEntities().isEmpty());
    }

    @Test
    public void testEntityManagerGetEntities()
    {
        EntityManager em = new EntityManager();
        assertTrue(em.getEntities().isEmpty());
    }

    @Test
    public void testEntityManagerGetEntityByUUID()
    {
        EntityManager em = new EntityManager();
        assertTrue(em.getEntityByUUID(null) == null);
    }
}
